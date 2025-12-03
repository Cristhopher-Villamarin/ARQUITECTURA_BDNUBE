package ec.edu.monster;

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

import ec.edu.monster.adapter.MovimientoAdapter;
import ec.edu.monster.model.MovimientoModel;
import ec.edu.monster.service.MovimientoService;
import ec.edu.monster.service.CuentaService;
import ec.edu.monster.model.CuentaModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class MovimientosActivity extends AppCompatActivity {

    private EditText etCuenta;
    private Button btnConsultar;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private TextView tvSaldoActual, tvTotalIngresos, tvTotalEgresos, tvSaldoNeto;
    private MovimientoService movimientoService;
    private CuentaService cuentaService;

    // Clasificación por CÓDIGO (recomendado)
    // Ajusta estas listas según tu catálogo real:
    private static final Set<String> COD_EGRESO = new HashSet<>();
    private static final Set<String> COD_INGRESO = new HashSet<>();
    static {
        // egresos
        COD_EGRESO.add("004"); // Retiro
        // ingresos
        COD_INGRESO.add("001"); // Apertura
        COD_INGRESO.add("003"); // Depósito
        COD_INGRESO.add("009"); // Transferencia (entrante en tu caso)
    }

    // Formateador de moneda USD para Ecuador
    private final NumberFormat money = NumberFormat.getCurrencyInstance(new Locale("es", "EC"));

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

            double saldoActual = (cuentaModel != null) ? cuentaModel.getDecCuenSaldo() : 0d;

            double totalIngresos = 0d;
            double totalEgresos = 0d;

            if (movimientos == null) movimientos = new ArrayList<>();

            for (MovimientoModel mov : movimientos) {
                double imp = (mov != null) ? mov.getImporteMovimiento() : 0d;
                String cod = safe(mov != null ? mov.getCodigoTipoMovimiento() : null);
                String desc = safe(mov != null ? mov.getTipoDescripcion() : null);

                // Clasifica por CÓDIGO primero; si no está mapeado, cae a descripción.
                if (COD_EGRESO.contains(cod)) {
                    totalEgresos += imp;
                } else if (COD_INGRESO.contains(cod)) {
                    totalIngresos += imp;
                } else {
                    // Fallback por descripción (case-insensitive, sin espacios)
                    String d = desc.toLowerCase(Locale.ROOT).trim();
                    if (d.equals("retiro")) {
                        totalEgresos += imp;
                    } else if (d.equals("deposito") || d.equals("depósito")
                            || d.equals("transferencia") || d.equals("apertura de cuenta")) {
                        totalIngresos += imp;
                    } else {
                        // Si llega un tipo desconocido, por seguridad NO lo contamos.
                        // (Puedes loguearlo para depurar)
                    }
                }
            }

            return new Result(movimientos, saldoActual, totalIngresos, totalEgresos);
        }

        @Override
        protected void onPostExecute(Result result) {
            progressBar.setVisibility(View.GONE);
            btnConsultar.setEnabled(true);

            tvSaldoActual.setText(money.format(result.saldoActual));
            tvTotalIngresos.setText(money.format(result.totalIngresos));
            tvTotalEgresos.setText(money.format(result.totalEgresos));
            tvSaldoNeto.setText(money.format(result.totalIngresos - result.totalEgresos));

            if (result.movimientos != null && !result.movimientos.isEmpty()) {
                recyclerView.setAdapter(new MovimientoAdapter(result.movimientos));
            } else {
                Toast.makeText(MovimientosActivity.this, "No se encontraron movimientos", Toast.LENGTH_SHORT).show();
                recyclerView.setAdapter(new MovimientoAdapter(new ArrayList<>()));
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

    private static String safe(String s) {
        return (s == null) ? "" : s;
    }
}
