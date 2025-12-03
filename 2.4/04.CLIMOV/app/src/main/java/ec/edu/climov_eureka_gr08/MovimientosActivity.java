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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ec.edu.climov_eureka_gr08.adapter.MovimientoAdapter;
import ec.edu.climov_eureka_gr08.model.CuentaModel;
import ec.edu.climov_eureka_gr08.model.MovimientoModel;
import ec.edu.climov_eureka_gr08.service.CuentaService;
import ec.edu.climov_eureka_gr08.service.MovimientoService;

import java.util.List;


public class MovimientosActivity extends AppCompatActivity {
    private EditText etCuenta;
    private Button btnConsultar;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private TextView tvSaldoActual, tvTotalIngresos, tvTotalEgresos, tvSaldoNeto;
    private MovimientoService movimientoService;
    private CuentaService cuentaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimientos);

        movimientoService = new MovimientoService();
        cuentaService = new CuentaService();

        etCuenta = findViewById(R.id.etCuenta);
        btnConsultar = findViewById(R.id.btnConsultar);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        tvSaldoActual = findViewById(R.id.tvSaldoActual);
        tvTotalIngresos = findViewById(R.id.tvTotalIngresos);
        tvTotalEgresos = findViewById(R.id.tvTotalEgresos);
        tvSaldoNeto = findViewById(R.id.tvSaldoNeto);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnConsultar.setOnClickListener(v -> {
            String cuenta = etCuenta.getText().toString().trim();

            if (cuenta.isEmpty()) {
                Toast.makeText(this, "Ingrese el número de cuenta", Toast.LENGTH_SHORT).show();
                return;
            }

            new ConsultarMovimientosTask().execute(cuenta);
        });
    }

    private class ConsultarMovimientosTask extends AsyncTask<String, Void, Result> {
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            btnConsultar.setEnabled(false);
        }

        @Override
        protected Result doInBackground(String... params) {
            String cuenta = params[0];

            List<MovimientoModel> movimientos = movimientoService.obtenerMovimientos(cuenta);
            CuentaModel cuentaModel = cuentaService.obtenerCuentaPorNumero(cuenta);

            double saldoActual = cuentaModel != null ? cuentaModel.getDecCuenSaldo() : 0;

            double totalIngresos = 0;
            double totalEgresos = 0;

            if (movimientos != null) {
                for (MovimientoModel mov : movimientos) {
                    String tipoDesc = mov.getTipoDescripcion();
                    if (tipoDesc != null && !tipoDesc.equals("Retiro")) {
                        totalIngresos += mov.getImporteMovimiento();
                    } else {
                        totalEgresos += mov.getImporteMovimiento();
                    }
                }
            }

            return new Result(movimientos, saldoActual, totalIngresos, totalEgresos);
        }

        @Override
        protected void onPostExecute(Result result) {
            progressBar.setVisibility(View.GONE);
            btnConsultar.setEnabled(true);

            tvSaldoActual.setText(String.format("$%.2f", result.saldoActual));
            tvTotalIngresos.setText(String.format("$%.2f", result.totalIngresos));
            tvTotalEgresos.setText(String.format("$%.2f", result.totalEgresos));
            // Saldo neto = ingresos - egresos
            tvSaldoNeto.setText(String.format("$%.2f", result.totalIngresos - result.totalEgresos));

            if (result.movimientos != null && !result.movimientos.isEmpty()) {
                MovimientoAdapter adapter = new MovimientoAdapter(result.movimientos);
                recyclerView.setAdapter(adapter);
            } else {
                Toast.makeText(MovimientosActivity.this, "No se encontraron movimientos", Toast.LENGTH_SHORT).show();
                recyclerView.setAdapter(new MovimientoAdapter(new java.util.ArrayList<>()));
            }
        }
    }

    private static class Result {
        List<MovimientoModel> movimientos;
        double saldoActual;
        double totalIngresos;
        double totalEgresos;

        Result(List<MovimientoModel> movimientos, double saldoActual, double totalIngresos, double totalEgresos) {
            this.movimientos = movimientos;
            this.saldoActual = saldoActual;
            this.totalIngresos = totalIngresos;
            this.totalEgresos = totalEgresos;
        }
    }
}
