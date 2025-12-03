package ec.edu.climov_eureka_gr08;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import ec.edu.climov_eureka_gr08.model.CuentaModel;
import ec.edu.climov_eureka_gr08.service.CuentaService;

public class DatosCuentaActivity extends AppCompatActivity{
    private EditText etCuenta;
    private Button btnConsultar;
    private ProgressBar progressBar;
    private CardView cardDatos;
    private TextView tvSaldo, tvContador;
    private CuentaService cuentaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_cuenta);

        cuentaService = new CuentaService();

        etCuenta = findViewById(R.id.etCuenta);
        btnConsultar = findViewById(R.id.btnConsultar);
        progressBar = findViewById(R.id.progressBar);
        cardDatos = findViewById(R.id.cardDatos);
        tvSaldo = findViewById(R.id.tvSaldo);
        tvContador = findViewById(R.id.tvContador);

        btnConsultar.setOnClickListener(v -> {
            String cuenta = etCuenta.getText().toString().trim();

            if (cuenta.isEmpty()) {
                Toast.makeText(this, "Ingrese el número de cuenta", Toast.LENGTH_SHORT).show();
                return;
            }

            new ConsultarCuentaTask().execute(cuenta);
        });
    }

    private class ConsultarCuentaTask extends AsyncTask<String, Void, CuentaModel> {
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            btnConsultar.setEnabled(false);
            cardDatos.setVisibility(View.GONE);
        }

        @Override
        protected CuentaModel doInBackground(String... params) {
            String cuenta = params[0];
            return cuentaService.obtenerCuentaPorNumero(cuenta);
        }

        @Override
        protected void onPostExecute(CuentaModel cuentaModel) {
            progressBar.setVisibility(View.GONE);
            btnConsultar.setEnabled(true);

            if (cuentaModel != null) {
                tvSaldo.setText(String.format("$%.2f", cuentaModel.getDecCuenSaldo()));
                tvContador.setText(String.valueOf(cuentaModel.getIntCuenContMov()));
                cardDatos.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(DatosCuentaActivity.this, "No se encontraron datos para la cuenta", Toast.LENGTH_SHORT).show();
                cardDatos.setVisibility(View.GONE);
            }
        }
    }
}
