package ec.edu.monster.util;


import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class SoapHelper {

    private static final String NAMESPACE = "http://ws.monster.edu.ec/";
    private static final String BASE_URL = "http://10.0.2.2:8080/ws_eureka_bank_soap_java/";

    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType XML = MediaType.parse("text/xml; charset=utf-8");

    public static String callWebService(String serviceName, String methodName, String soapBody) {
        try {
            String soapAction = NAMESPACE + methodName;
            String soapRequest = buildSoapEnvelope(methodName, soapBody);

            RequestBody body = RequestBody.create(soapRequest, XML);
            Request request = new Request.Builder()
                    .url(BASE_URL + serviceName)
                    .addHeader("SOAPAction", "\"" + soapAction + "\"")
                    .addHeader("Content-Type", "text/xml; charset=utf-8")
                    .post(body)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    return response.body().string();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String buildSoapEnvelope(String methodName, String soapBody) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"" +
                " xmlns:ws=\"" + NAMESPACE + "\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<ws:" + methodName + ">" +
                soapBody +
                "</ws:" + methodName + ">" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";
    }

    public static String getNamespace() {
        return NAMESPACE;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }
}
