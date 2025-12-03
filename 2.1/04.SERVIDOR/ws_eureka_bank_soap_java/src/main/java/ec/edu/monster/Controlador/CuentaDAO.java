package ec.edu.monster.Controlador;


import ec.edu.monster.db.AccesoBD;
import ec.edu.monster.modelo.CuentaModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CuentaDAO {

    // Actualizar el saldo de la cuenta cuando se hace un Movimiento (Retiro, Deposito, Transferencia)
    public boolean actualizarSaldoCuenta(String codigoCuenta, String valorMovimiento) throws SQLException {
        String sql = "UPDATE cuenta "
                + "SET dec_cuensaldo = dec_cuensaldo + ?, "
                + "int_cuencontmov = int_cuencontmov + 1 "
                + "WHERE chr_cuencodigo = ?";
        try (Connection connection = AccesoBD.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, Double.parseDouble(valorMovimiento)); // El valor que deseas sumar (positivo o negativo)
            statement.setString(2, codigoCuenta);   // El código de la cuenta a actualizar

            int rowsUpdated = statement.executeUpdate(); // Ejecuta la actualización
            return rowsUpdated > 0; // Retorna true si se actualizó al menos una fila
        }
    }

    // Método para obtener una cuenta por su Código
    public CuentaModel obtenerCuentaPorCodigo(String codigoCuenta) throws SQLException {
        String sql = "SELECT * FROM cuenta WHERE chr_cuencodigo = ?";
        try (Connection connection = AccesoBD.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, codigoCuenta);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                CuentaModel cuenta = new CuentaModel();
                cuenta.setChrCuenCodigo(resultSet.getString("chr_cuencodigo"));
                cuenta.setChrMoneCodigo(resultSet.getString("chr_monecodigo"));
                cuenta.setChrSucucodigo(resultSet.getString("chr_sucucodigo"));
                cuenta.setChrEmplCreaCuenta(resultSet.getString("chr_emplcreacuenta"));
                cuenta.setChrClieCodigo(resultSet.getString("chr_cliecodigo"));
                cuenta.setDecCuenSaldo(resultSet.getDouble("dec_cuensaldo"));
                cuenta.setDttCuenFechaCreacion(resultSet.getDate("dtt_cuenfechacreacion"));
                cuenta.setVchCuenEstado(resultSet.getString("vch_cuenestado"));
                cuenta.setIntCuenContMov(resultSet.getInt("int_cuencontmov"));
                cuenta.setChrCuenClave(resultSet.getString("chr_cuenclave"));
                return cuenta;
            }
        }
        return null;
    }
}
