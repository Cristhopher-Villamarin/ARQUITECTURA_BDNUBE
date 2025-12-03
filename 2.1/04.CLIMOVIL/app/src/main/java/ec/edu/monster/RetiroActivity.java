package ec.edu.monster;


import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ec.edu.monster.service.CuentaService;

public class RetiroActivity extends AppCompatActivity {

    private EditText etCuenta, etMonto;
    private Button btnRealizar;
    private ProgressBar progressBar;
    private CuentaService cuentaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retiro);

        cuentaService = new CuentaService();

        etCuenta = findViewById(R.id.etCuenta);
        etMonto = findViewById(R.id.etMonto);
        btnRealizar = findViewById(R.id.btnRealizar);
        progressBar = findViewById(R.id.progressBar);

        btnRealizar.setOnClickListener(v -> {
            String cuenta = etCuenta.getText().toString().trim();
            String monto = etMonto.getText().toString().trim();

            if (cuenta.isEmpty() || monto.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            new RetiroTask().execute(cuenta, monto);
        });
    }

    private class RetiroTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            btnRealizar.setEnabled(false);
        }

        @Override
        protected Boolean doInBackground(String... params) {
            String cuenta = params[0];
            String monto = params[1];

            return cuentaService.realizarRetiro(cuenta, monto);
        }

        @Override
        protected void onPostExecute(Boolean success) {
            progressBar.setVisibility(View.GONE);
            btnRealizar.setEnabled(true);

            if (success) {
                Toast.makeText(RetiroActivity.this, "Retiro realizado con éxito", Toast.LENGTH_SHORT).show();
                etCuenta.setText("");
                etMonto.setText("");
            } else {
                Toast.makeText(RetiroActivity.this, "Error al realizar el retiro", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

