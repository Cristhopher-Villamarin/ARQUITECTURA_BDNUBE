package ec.edu.monster.service;

import ec.edu.monster.util.SoapHelper;

public class LoginService {

    public boolean auth(String username, String password) {
        try {
            String soapBody = "<username>" + username + "</username>" +
                    "<password>" + password + "</password>";

            String response = SoapHelper.callWebService("WSLogin", "auth", soapBody);

            // Parsear respuesta XML (simplificado)
            if (response != null) {
                // Buscar <return>true</return> o <return>false</return>
                if (response.contains("<return>true</return>") ||
                        response.contains("<ns2:return>true</ns2:return>")) {
                    return true;
                }
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

