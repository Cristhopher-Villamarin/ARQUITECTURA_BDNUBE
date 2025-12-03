package ec.edu.climov_eureka_gr08;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ec.edu.climov_eureka_gr08.model.OperacionResponse;
import ec.edu.climov_eureka_gr08.service.CuentaService;

public class DepositoActivity extends AppCompatActivity{
    private EditText etCuenta, etMonto;
    private Button btnRealizar;
    private ProgressBar progressBar;
    private CuentaService cuentaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposito);

        cuentaService = new CuentaService();

        etCuenta = findViewById(R.id.etCuenta);
        etMonto = findViewById(R.id.etMonto);
        btnRealizar = findViewById(R.id.btnRealizar);
        progressBar = findViewById(R.id.progressBar);

        btnRealizar.setOnClickListener(v -> {
            String cuenta = etCuenta.getText().toString().trim();
            String montoStr = etMonto.getText().toString().trim();

            if (cuenta.isEmpty() || montoStr.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double monto = Double.parseDouble(montoStr);
                if (monto <= 0) {
                    Toast.makeText(this, "El monto debe ser mayor a cero", Toast.LENGTH_SHORT).show();
                    return;
                }
                new DepositoTask().execute(cuenta, montoStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Ingrese un monto válido", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class DepositoTask extends AsyncTask<String, Void, OperacionResponse> {
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            btnRealizar.setEnabled(false);
        }

        @Override
        protected OperacionResponse doInBackground(String... params) {
            String cuenta = params[0];
            double monto = Double.parseDouble(params[1]);

            return cuentaService.realizarDeposito(cuenta, monto);
        }

        @Override
        protected void onPostExecute(OperacionResponse response) {
            progressBar.setVisibility(View.GONE);
            btnRealizar.setEnabled(true);

            if (response != null && response.isSuccess()) {
                Toast.makeText(DepositoActivity.this,
                        response.getMensaje() + "\nSaldo actual: $" + String.format("%.2f", response.getSaldo()),
                        Toast.LENGTH_LONG).show();
                etCuenta.setText("");
                etMonto.setText("");
            } else {
                String mensaje = response != null ? response.getMensaje() : "Error al realizar el depósito";
                Toast.makeText(DepositoActivity.this, mensaje, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
