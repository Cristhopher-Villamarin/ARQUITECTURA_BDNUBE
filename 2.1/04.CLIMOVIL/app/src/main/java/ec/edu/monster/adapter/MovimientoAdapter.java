package ec.edu.monster.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ec.edu.monster.R;
import ec.edu.monster.model.MovimientoModel;

import java.util.List;

public class MovimientoAdapter extends RecyclerView.Adapter<MovimientoAdapter.ViewHolder> {

    private List<MovimientoModel> movimientos;

    public MovimientoAdapter(List<MovimientoModel> movimientos) {
        this.movimientos = movimientos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movimiento, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovimientoModel movimiento = movimientos.get(position);

        holder.tvNro.setText(String.valueOf(movimiento.getNumeroMovimiento()));
        holder.tvFecha.setText(movimiento.getFechaMovimiento());
        holder.tvTipo.setText(movimiento.getCodigoTipoMovimiento());

        // Determinar descripción basada en el código de tipo de movimiento
        String descripcion = movimiento.getTipoDescripcion();
        String codigoTipo = movimiento.getCodigoTipoMovimiento();

        if ("009".equals(codigoTipo)) {
            descripcion = "Transferencia - Débito";
        } else if ("008".equals(codigoTipo)) {
            descripcion = "Transferencia - Crédito";
        }

        holder.tvDescripcion.setText(descripcion);
        holder.tvImporte.setText(String.format("$%.2f", movimiento.getImporteMovimiento()));

        // Determinar la etiqueta y valor según el tipo de movimiento
        String tipoMov = movimiento.getCodigoTipoMovimiento();
        String cuentaRef = movimiento.getCuentaReferencia();

        if (tipoMov != null && (tipoMov.equals("009") || tipoMov.equals("TRA"))) {
            // Transferencia salida - mostrar cuenta destino
            holder.tvCtaRefLabel.setText("Cta. Destino");
            holder.tvCtaRef.setText(cuentaRef != null && !cuentaRef.trim().isEmpty() ? cuentaRef : "N/A");
        } else if (tipoMov != null && tipoMov.equals("008")) {
            // Transferencia entrada - mostrar cuenta origen
            holder.tvCtaRefLabel.setText("Cta. Origen");
            holder.tvCtaRef.setText(cuentaRef != null && !cuentaRef.trim().isEmpty() ? cuentaRef : "N/A");
        } else {
            // Otros tipos de movimiento
            holder.tvCtaRefLabel.setText("Cta. Ref.");
            holder.tvCtaRef.setText(cuentaRef != null && !cuentaRef.trim().isEmpty() ? cuentaRef : "N/A");
        }

        holder.tvSaldo.setText(String.format("$%.2f", movimiento.getSaldo()));
    }

    @Override
    public int getItemCount() {
        return movimientos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNro, tvFecha, tvTipo, tvDescripcion, tvImporte, tvCtaRef, tvSaldo, tvCtaRefLabel;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNro = itemView.findViewById(R.id.tvNro);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvImporte = itemView.findViewById(R.id.tvImporte);
            tvCtaRef = itemView.findViewById(R.id.tvCtaRef);
            tvCtaRefLabel = itemView.findViewById(R.id.tvCtaRefLabel);
            tvSaldo = itemView.findViewById(R.id.tvSaldo);
        }
    }
}