package ec.edu.climov_eureka_gr08.util;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

/**
 * Helper para configurar OkHttpClient
 * Soporta tanto HTTP como HTTPS con certificados autofirmados (solo para desarrollo)
 */
public class OkHttpClientHelper {

    /**
     * Crea un OkHttpClient simple para HTTP con timeouts configurados
     */
    public static OkHttpClient createHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    /**
     * Crea un OkHttpClient para HTTPS con certificados autofirmados (solo para desarrollo)
     * Mantiene compatibilidad con código existente
     */
    public static OkHttpClient createUnsafeOkHttpClient() {
        // Para HTTP simple, usar el cliente básico
        return createHttpClient();
    }
}