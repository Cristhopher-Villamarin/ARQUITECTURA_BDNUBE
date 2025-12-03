package ec.edu.climov_eureka_gr08.util;

public class ApiConfig {
    // URL base del servidor RESTful
    // Para desarrollo local con emulador Android: http://10.0.2.2:5046/api/
    // Para dispositivo físico: http://192.168.1.25:5046/api/
    // NOTA: 10.0.2.2 es la IP especial del emulador que apunta a localhost de la PC
    public static final String BASE_URL = "http://10.95.194.42:5046/api/";

    // Endpoints
    public static final String LOGIN = "Login";
    public static final String CUENTA = "Cuenta/";
    public static final String MOVIMIENTO = "Movimiento";

    // Métodos de cuenta
    public static final String DEPOSITO = "deposito";
    public static final String RETIRO = "retiro";
    public static final String TRANSFERENCIA = "transferencia";
}
