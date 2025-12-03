package ec.edu.climov_eureka_gr08.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ec.edu.climov_eureka_gr08.model.MovimientoModel;
import ec.edu.climov_eureka_gr08.util.ApiConfig;
import ec.edu.climov_eureka_gr08.util.OkHttpClientHelper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovimientoService {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private final OkHttpClient client;
    private final Gson gson;

    public MovimientoService() {
        // Usar OkHttpClient configurado para HTTPS con certificados autofirmados
        this.client = OkHttpClientHelper.createUnsafeOkHttpClient();
        this.gson = new Gson();
    }

    public List<MovimientoModel> obtenerMovimientos(String cuenta) {
        try {
            Map<String, Object> request = new HashMap<>();
            request.put("numcuenta", cuenta);

            String json = gson.toJson(request);
            RequestBody body = RequestBody.create(json, JSON);
            Request httpRequest = new Request.Builder()
                    .url(ApiConfig.BASE_URL + ApiConfig.MOVIMIENTO)
                    .post(body)
                    .build();

            try (Response response = client.newCall(httpRequest).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = response.body().string();
                    Type listType = new TypeToken<List<MovimientoModel>>(){}.getType();
                    List<MovimientoModel> movimientos = gson.fromJson(responseBody, listType);
                    return movimientos != null ? movimientos : new ArrayList<>();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
