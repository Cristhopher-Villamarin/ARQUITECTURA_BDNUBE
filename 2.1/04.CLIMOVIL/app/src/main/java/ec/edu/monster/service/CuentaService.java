package ec.edu.monster.service;


import ec.edu.monster.model.CuentaModel;
import ec.edu.monster.util.SoapHelper;

public class CuentaService {

    public boolean realizarDeposito(String cuenta, String monto, String tipo, String cd) {
        try {
            String soapBody = "<cuenta>" + cuenta + "</cuenta>" +
                    "<monto>" + monto + "</monto>" +
                    "<tipo>" + tipo + "</tipo>" +
                    (cd != null ? "<cd>" + cd + "</cd>" : "");

            String response = SoapHelper.callWebService("WSCuenta", "cuenta", soapBody);

            if (response != null) {
                return response.contains("<return>true</return>") ||
                        response.contains("<ns2:return>true</ns2:return>");
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public CuentaModel obtenerCuentaPorNumero(String cuenta) {
        try {
            String soapBody = "<cuenta>" + cuenta + "</cuenta>";

            String response = SoapHelper.callWebService("WSCuenta", "obtenerCuentaPorNumero", soapBody);

            if (response != null && response.contains("decCuenSaldo")) {
                CuentaModel cuentaModel = new CuentaModel();

                // Parsear XML manualmente (simplificado)
                String saldoStr = extractTagContent(response, "decCuenSaldo");
                String contadorStr = extractTagContent(response, "intCuenContMov");

                if (saldoStr != null) {
                    cuentaModel.setDecCuenSaldo(Double.parseDouble(saldoStr));
                }
                if (contadorStr != null) {
                    cuentaModel.setIntCuenContMov(Integer.parseInt(contadorStr));
                }
                cuentaModel.setStrCuenNumero(cuenta);

                return cuentaModel;
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String extractTagContent(String xml, String tag) {
        try {
            String openTag = "<" + tag + ">";
            String closeTag = "</" + tag + ">";
            int start = xml.indexOf(openTag);
            int end = xml.indexOf(closeTag);

            if (start != -1 && end != -1) {
                return xml.substring(start + openTag.length(), end).trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método de conveniencia para retiro
    public boolean realizarRetiro(String cuenta, String monto) {
        return realizarDeposito(cuenta, monto, "RET", null);
    }

    // Método de conveniencia para transferencia
    // El servidor maneja la transferencia completa en una sola llamada:
    // - Resta de cuentaOrigen (primer parámetro)
    // - Suma a cuentaDestino (cuarto parámetro cd)
    public boolean realizarTransferencia(String cuentaOrigen, String cuentaDestino, String monto) {
        // Llamar al servicio con tipo TRA
        // cuenta = cuentaOrigen (de donde se resta)
        // cd = cuentaDestino (a donde se suma)
        return realizarDeposito(cuentaOrigen, monto, "TRA", cuentaDestino);
    }
}

