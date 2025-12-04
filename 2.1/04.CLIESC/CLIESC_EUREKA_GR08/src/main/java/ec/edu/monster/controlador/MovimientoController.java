package ec.edu.monster.controlador;

import ec.edu.monster.vista.MovimientoView;
import ec.edu.monster.ws.MovimientoModel;
import ec.edu.monster.service.MovimientoService;
import ec.edu.monster.service.CuentaService;
import ec.edu.monster.ws.CuentaModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MovimientoController {

    private MovimientoService movimientoService;
    private CuentaService cuentaService;

    public MovimientoController() {
        this.movimientoService = new MovimientoService();
        this.cuentaService = new CuentaService();
    }

    /**
     * Método para cargar y mostrar los movimientos en la vista proporcionada.
     *
     * @param cuenta         Número de cuenta a buscar.
     * @param movimientoView Instancia de la vista donde se mostrarán los
     *                       resultados.
     */
    public void cargarMovimientos(String cuenta, MovimientoView movimientoView) {
        try {
            List<MovimientoModel> movimientos = movimientoService.obtenerMovimientos(cuenta);
            CuentaModel cuentaModel = cuentaService.obtenerCuentaPorNumero(cuenta);

            JPanel panelResultados = new JPanel();
            panelResultados.setLayout(new BoxLayout(panelResultados, BoxLayout.Y_AXIS));
            panelResultados.setBackground(Color.WHITE);

            if (movimientos == null || movimientos.isEmpty()) {
                JLabel noResultados = new JLabel("No se encontraron movimientos para la cuenta: " + cuenta);
                noResultados.setFont(new Font("Segoe UI", Font.BOLD, 16));
                noResultados.setForeground(new Color(180, 40, 40));
                noResultados.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                panelResultados.add(noResultados);
            } else {
                // Crear celdas para cada movimiento
                for (MovimientoModel mov : movimientos) {

                    // Determinar descripción basada en el código de tipo de movimiento
                    String descripcion = mov.getTipoDescripcion();
                    String codigoTipo = mov.getCodigoTipoMovimiento();

                    if ("009".equals(codigoTipo)) {
                        descripcion = "Transferencia - Débito";
                    } else if ("008".equals(codigoTipo)) {
                        descripcion = "Transferencia - Crédito";
                    }

                    String saldoTexto;
                    if (mov.getSaldo() != 0) {
                        // Asumimos que el backend ya envía el saldo acumulativo en cada movimiento
                        saldoTexto = "Saldo después: $" + String.format("%.2f", mov.getSaldo());
                    } else {
                        // Si por alguna razón viene 0, lo mostramos como "no disponible"
                        saldoTexto = "Saldo después: N/D";
                    }

                    JPanel celda = movimientoView.crearCelda(
                            "Cuenta: " + mov.getCodigoCuenta(),
                            "Fecha: " + mov.getFechaMovimiento(),
                            "Movimiento: " + mov.getNumeroMovimiento(),
                            "Descripción: " + descripcion,
                            "Tipo Mov.: " + mov.getCodigoTipoMovimiento(),
                            "Importe: $" + String.format("%.2f", mov.getImporteMovimiento()),
                            saldoTexto);
                    panelResultados.add(celda);
                }
                // Resumen de totales
                double totalIngresos = movimientos.stream()
                        .filter(m -> m.getTipoDescripcion() != null && !m.getTipoDescripcion().equals("Retiro"))
                        .mapToDouble(MovimientoModel::getImporteMovimiento)
                        .sum();

                double totalEgresos = movimientos.stream()
                        .filter(m -> m.getTipoDescripcion() != null && m.getTipoDescripcion().equals("Retiro"))
                        .mapToDouble(MovimientoModel::getImporteMovimiento)
                        .sum();

                JPanel resumenPanel = new JPanel(new GridLayout(3, 2, 10, 5));
                resumenPanel.setBackground(new Color(248, 248, 255));
                resumenPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(0x76, 0x4b, 0xa2), 2, true),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));

                JLabel saldoCuentaLabel = new JLabel("Saldo de la Cuenta: $"
                        + (cuentaModel != null ? String.format("%.2f", cuentaModel.getDecCuenSaldo()) : "N/A"));
                saldoCuentaLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
                saldoCuentaLabel.setForeground(new Color(40, 40, 40));

                JLabel totalMovimientosLabel = new JLabel(
                        "Total Movimientos: " + (cuentaModel != null ? cuentaModel.getIntCuenContMov() : "N/A"));
                totalMovimientosLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
                totalMovimientosLabel.setForeground(new Color(40, 40, 40));

                JLabel totalIngresosLabel = new JLabel("Total Ingresos: $" + String.format("%.2f", totalIngresos));
                totalIngresosLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                totalIngresosLabel.setForeground(new Color(0, 140, 70));

                JLabel totalEgresosLabel = new JLabel(
                        "Total Egresos: $" + String.format("%.2f", Math.abs(totalEgresos)));
                totalEgresosLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                totalEgresosLabel.setForeground(new Color(180, 40, 40));

                JLabel saldoNetoLabel = new JLabel(
                        "Saldo Neto: $" + String.format("%.2f", totalIngresos + totalEgresos));
                saldoNetoLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
                saldoNetoLabel.setForeground(new Color(0x76, 0x4b, 0xa2));

                JLabel espacio = new JLabel("");

                resumenPanel.add(saldoCuentaLabel);
                resumenPanel.add(totalMovimientosLabel);
                resumenPanel.add(totalIngresosLabel);
                resumenPanel.add(totalEgresosLabel);
                resumenPanel.add(saldoNetoLabel);
                resumenPanel.add(espacio);

                panelResultados.add(resumenPanel);
            }

            // Reemplazar el contenido del JScrollPane con los nuevos resultados
            movimientoView.getjScrollPane1().setViewportView(panelResultados);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(movimientoView,
                    "Error al obtener los movimientos o datos de la cuenta: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
