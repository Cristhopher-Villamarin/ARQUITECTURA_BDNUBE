package ec.edu.monster.Controlador;

import ec.edu.monster.db.AccesoBD;
import ec.edu.monster.modelo.MovimientoModel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovimientoDAO {
    
    // TABLAS: MOVIMIENTO Y TIPOMOVIMIENTO
    public List<MovimientoModel> obtenerMovimientosPorCuenta(String codigoCuenta) throws SQLException {
        String sql = "SELECT m.chr_cuencodigo, m.int_movinumero, m.dtt_movifecha, "
                + "m.chr_emplcodigo, m.chr_tipocodigo, m.dec_moviimporte, m.chr_cuenreferencia, "
                + "t.vch_tipodescripcion AS tipoDescripcion, t.vch_tipoaccion AS tipoAccion "
                + "FROM movimiento AS m "
                + "INNER JOIN tipomovimiento AS t ON t.chr_tipocodigo = m.chr_tipocodigo "
                + "WHERE m.chr_cuencodigo = ? "
                + "ORDER BY m.dtt_movifecha ASC, m.int_movinumero ASC";

        List<MovimientoModel> movimientosConSaldo = new ArrayList<>();
        double saldoAcumulado = 0.0;
        
        try (Connection connection = AccesoBD.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, codigoCuenta);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                MovimientoModel movimiento = new MovimientoModel();
                java.sql.Date date = resultSet.getDate("dtt_movifecha");
                System.out.println("Raw date: " + date);
                if (date != null) {
                    movimiento.setFechaMovimiento(date.toString());
                } else {
                    System.out.println("Fecha Movimiento is null for row: " + resultSet.getRow());
                }
                movimiento.setCodigoCuenta(resultSet.getString("chr_cuencodigo"));
                movimiento.setNumeroMovimiento(resultSet.getInt("int_movinumero"));
                movimiento.setCodigoEmpleado(resultSet.getString("chr_emplcodigo"));
                movimiento.setCodigoTipoMovimiento(resultSet.getString("chr_tipocodigo"));
                movimiento.setTipoDescripcion(resultSet.getString("tipoDescripcion"));
                movimiento.setImporteMovimiento(resultSet.getDouble("dec_moviimporte"));
                movimiento.setCuentaReferencia(resultSet.getString("chr_cuenreferencia"));
                
                // Calcular el saldo acumulativo
                String tipoAccion = resultSet.getString("tipoAccion");
                double importe = movimiento.getImporteMovimiento();
                
                if ("INGRESO".equals(tipoAccion)) {
                    saldoAcumulado += importe;
                } else if ("SALIDA".equals(tipoAccion)) {
                    saldoAcumulado -= importe;
                }
                
                movimiento.setSaldo(saldoAcumulado);
                movimientosConSaldo.add(movimiento);
            }
        }
        
        // Ordenar por fecha descendente (más reciente primero) y luego por número de movimiento
        movimientosConSaldo.sort((m1, m2) -> {
            int fechaCompare = m2.getFechaMovimiento().compareTo(m1.getFechaMovimiento());
            if (fechaCompare != 0) {
                return fechaCompare;
            }
            // Si las fechas son iguales, ordenar por número de movimiento descendente
            return Integer.compare(m2.getNumeroMovimiento(), m1.getNumeroMovimiento());
        });
        
        return movimientosConSaldo;
    }

    // Método para registrar un movimiento
    public void registrarMovimiento(MovimientoModel movimiento) throws SQLException {
        String sql = "INSERT INTO movimiento (chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, "
                + "chr_tipocodigo, dec_moviimporte, chr_cuenreferencia) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = AccesoBD.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, movimiento.getCodigoCuenta());
            statement.setInt(2, movimiento.getNumeroMovimiento());
            statement.setDate(3, Date.valueOf(movimiento.getFechaMovimiento())); // Convert LocalDate to Date
            statement.setString(4, movimiento.getCodigoEmpleado());
            statement.setString(5, movimiento.getCodigoTipoMovimiento());
            statement.setDouble(6, movimiento.getImporteMovimiento());
            statement.setString(7, movimiento.getCuentaReferencia());
            statement.executeUpdate();
        }
    }
}
