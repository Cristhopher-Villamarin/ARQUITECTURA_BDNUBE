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

public class TransferenciaActivity extends AppCompatActivity{
    private EditText etCuentaOrigen, etCuentaDestino, etMonto;
    private Button btnRealizar;
    private ProgressBar progressBar;
    private CuentaService cuentaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencia);

        cuentaService = new CuentaService();

        etCuentaOrigen = findViewById(R.id.etCuentaOrigen);
        etCuentaDestino = findViewById(R.id.etCuentaDestino);
        etMonto = findViewById(R.id.etMonto);
        btnRealizar = findViewById(R.id.btnRealizar);
        progressBar = findViewById(R.id.progressBar);

        btnRealizar.setOnClickListener(v -> {
            String cuentaOrigen = etCuentaOrigen.getText().toString().trim();
            String cuentaDestino = etCuentaDestino.getText().toString().trim();
            String montoStr = etMonto.getText().toString().trim();

            if (cuentaOrigen.isEmpty() || cuentaDestino.isEmpty() || montoStr.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (cuentaOrigen.equals(cuentaDestino)) {
                Toast.makeText(this, "La cuenta origen y destino no pueden ser iguales", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double monto = Double.parseDouble(montoStr);
                if (monto <= 0) {
                    Toast.makeText(this, "El monto debe ser mayor a cero", Toast.LENGTH_SHORT).show();
                    return;
                }
                new TransferenciaTask().execute(cuentaOrigen, cuentaDestino, montoStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Ingrese un monto válido", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class TransferenciaTask extends AsyncTask<String, Void, OperacionResponse> {
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            btnRealizar.setEnabled(false);
        }

        @Override
        protected OperacionResponse doInBackground(String... params) {
            String cuentaOrigen = params[0];
            String cuentaDestino = params[1];
            double monto = Double.parseDouble(params[2]);

            return cuentaService.realizarTransferencia(cuentaOrigen, cuentaDestino, monto);
        }

        @Override
        protected void onPostExecute(OperacionResponse response) {
            progressBar.setVisibility(View.GONE);
            btnRealizar.setEnabled(true);

            if (response != null && response.isSuccess()) {
                Toast.makeText(TransferenciaActivity.this,
                        response.getMensaje() + "\nSaldo actual: $" + String.format("%.2f", response.getSaldo()),
                        Toast.LENGTH_LONG).show();
                etCuentaOrigen.setText("");
                etCuentaDestino.setText("");
                etMonto.setText("");
            } else {
                String mensaje = response != null ? response.getMensaje() : "Error al realizar la transferencia";
                Toast.makeText(TransferenciaActivity.this, mensaje, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
