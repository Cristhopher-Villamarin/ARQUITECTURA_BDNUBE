package ec.edu.climov_eureka_gr08.service;

import com.google.gson.Gson;

import ec.edu.climov_eureka_gr08.model.LoginRequest;
import ec.edu.climov_eureka_gr08.model.LoginResponse;
import ec.edu.climov_eureka_gr08.util.ApiConfig;
import ec.edu.climov_eureka_gr08.util.OkHttpClientHelper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class LoginService {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private final OkHttpClient client;
    private final Gson gson;

    public LoginService() {
        // Usar OkHttpClient configurado para HTTPS con certificados autofirmados
        this.client = OkHttpClientHelper.createUnsafeOkHttpClient();
        this.gson = new Gson();
    }

    public boolean auth(String username, String password) {
        try {
            LoginRequest request = new LoginRequest(username, password);
            String json = gson.toJson(request);

            RequestBody body = RequestBody.create(json, JSON);
            Request httpRequest = new Request.Builder()
                    .url(ApiConfig.BASE_URL + ApiConfig.LOGIN)
                    .post(body)
                    .build();

            try (Response response = client.newCall(httpRequest).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = response.body().string();
                    LoginResponse loginResponse = gson.fromJson(responseBody, LoginResponse.class);
                    return loginResponse != null && loginResponse.isSuccess();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
