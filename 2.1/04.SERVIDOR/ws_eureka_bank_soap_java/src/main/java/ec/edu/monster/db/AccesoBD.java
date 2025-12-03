package ec.edu.monster.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoBD {

    private static final String URL =
        "jdbc:mysql://52.189.79.108:3306/eurekabank"
        + "?useSSL=false"
        + "&serverTimezone=UTC"
        + "&allowPublicKeyRetrieval=true"; // útil con MySQL 8 y cuentas nuevas

    private static final String USER = "root";     // o "MONSTER"
    private static final String PASS = "root"; // o "MONSTER9"

     public static Connection getConnection() throws SQLException {
        try {
            // Con JDBC 4+ no es obligatorio, pero mantiene compatibilidad
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el driver MySQL (com.mysql.cj.jdbc.Driver).", e);
        } catch (SQLException e) {
            // re-lanzamos con contexto
            throw new SQLException("Error al conectar a MySQL: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new SQLException("Error inesperado al conectar.", e);
        }
    }
     public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
