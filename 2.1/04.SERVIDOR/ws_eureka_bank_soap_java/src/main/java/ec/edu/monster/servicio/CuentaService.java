package ec.edu.monster.servicio;

import ec.edu.monster.Controlador.CuentaDAO;
import ec.edu.monster.Controlador.MovimientoDAO;
import ec.edu.monster.modelo.CuentaModel;
import ec.edu.monster.modelo.MovimientoModel;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class CuentaService {

    private MovimientoDAO movimientoDAO = new MovimientoDAO();
    private CuentaDAO cuentaDAO = new CuentaDAO();

    // Método para actualizar el saldo de la cuenta y registrar el movimiento
    public boolean actualizarSaldoYRegistrarMovimiento(String codigoCuenta, String valorMovimiento, String tipo, String cuentaDest) {
        try {
            double importe = Double.parseDouble(valorMovimiento);
            String codTipo = "";

            // Validate the operation type
            if (tipo.equalsIgnoreCase("RET")) {  // Retiro
                codTipo = "004";

                CuentaModel cuenta = cuentaDAO.obtenerCuentaPorCodigo(codigoCuenta);
                if (cuenta.getDecCuenSaldo() < importe) {
                    return false;
                }

                if (!cuentaDAO.actualizarSaldoCuenta(codigoCuenta, String.valueOf(-importe))) {
                    return false;  // Return false if balance couldn't be updated
                }
            } else if (tipo.equalsIgnoreCase("DEP")) {
                codTipo = "003";
                if (!cuentaDAO.actualizarSaldoCuenta(codigoCuenta, valorMovimiento)) {
                    return false;  // Return false if balance couldn't be updated
                }
            } else if (tipo.equalsIgnoreCase("TRA")) {  // Transferencia
                // Check that the destination account is provided
                codTipo = "009";
                if (cuentaDest == null || cuentaDest.isEmpty()) {
                    throw new IllegalArgumentException("Cuenta destino es obligatoria para transferencias.");
                }

                CuentaModel cuentaOrigen = cuentaDAO.obtenerCuentaPorCodigo(codigoCuenta);
                if (cuentaOrigen.getDecCuenSaldo() < importe) {
                    return false;
                }

                // Deduct from source account
                if (!cuentaDAO.actualizarSaldoCuenta(codigoCuenta, String.valueOf(-importe))) {
                    return false;  // Return false if balance couldn't be updated
                }

                // Add to destination account
                if (!cuentaDAO.actualizarSaldoCuenta(cuentaDest, valorMovimiento)) {
                    // Rollback source account update if destination update fails
                    cuentaDAO.actualizarSaldoCuenta(codigoCuenta, valorMovimiento);
                    return false;
                }
            } else {
                throw new IllegalArgumentException("Tipo de movimiento no soportado: " + tipo);
            }

            // Register the movement
            int numeroMovimiento = obtenerSiguienteNumeroMovimiento(codigoCuenta);

            MovimientoModel movimiento = new MovimientoModel();
            movimiento.setCodigoCuenta(codigoCuenta);
            movimiento.setNumeroMovimiento(numeroMovimiento);
            movimiento.setFechaMovimiento(LocalDate.now().toString());
            movimiento.setCodigoEmpleado("0001");  // Update as needed
            movimiento.setCodigoTipoMovimiento(codTipo);
            movimiento.setImporteMovimiento(importe);
            
            // Para transferencias, guardar la cuenta destino en cuentaReferencia
            if (tipo.equalsIgnoreCase("TRA")) {
                movimiento.setCuentaReferencia(cuentaDest);
            }

            movimientoDAO.registrarMovimiento(movimiento);

            // Register the movement for the destination account in case of transfer
            if (tipo.equalsIgnoreCase("TRA")) {
                int numeroMovimientoDest = obtenerSiguienteNumeroMovimiento(cuentaDest);

                MovimientoModel movimientoDest = new MovimientoModel();
                movimientoDest.setCodigoCuenta(cuentaDest);
                movimientoDest.setNumeroMovimiento(numeroMovimientoDest);
                movimientoDest.setFechaMovimiento(LocalDate.now().toString());
                movimientoDest.setCodigoEmpleado("0001");  // Update as needed
                movimientoDest.setCodigoTipoMovimiento("008");
                movimientoDest.setImporteMovimiento(importe);
                // Para transferencias entrantes, guardar la cuenta origen en cuentaReferencia
                movimientoDest.setCuentaReferencia(codigoCuenta);

                movimientoDAO.registrarMovimiento(movimientoDest);
            }

            return true;
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener el siguiente número de movimiento para una cuenta
    private int obtenerSiguienteNumeroMovimiento(String codigoCuenta) throws SQLException {
        List<MovimientoModel> movimientos = movimientoDAO.obtenerMovimientosPorCuenta(codigoCuenta);
        int numeroMovimiento = 1;  // Empezamos con el número de movimiento 1 por defecto

        // Buscamos el último número de movimiento
        for (MovimientoModel movimiento : movimientos) {
            if (movimiento.getNumeroMovimiento() >= numeroMovimiento) {
                numeroMovimiento = movimiento.getNumeroMovimiento() + 1;
            }
        }
        return numeroMovimiento;
    }

    public CuentaModel obtenerCuenta(String codigoCuenta) throws SQLException {
        return cuentaDAO.obtenerCuentaPorCodigo(codigoCuenta);
    }
}
