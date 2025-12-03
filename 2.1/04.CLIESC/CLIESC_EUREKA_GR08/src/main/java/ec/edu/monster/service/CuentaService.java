package ec.edu.monster.service;

import ec.edu.monster.ws.CuentaModel;
import ec.edu.monster.ws.WSCuenta;
import ec.edu.monster.ws.WSCuenta_Service;
import java.util.List;

public class CuentaService {
    
    // Existing method for deposit
    public boolean realizarDeposito(String cuenta, String monto, String tipo, String cd) {
        WSCuenta_Service servicio = new WSCuenta_Service();
        WSCuenta port = servicio.getWSCuentaPort();
        return port.cuenta(cuenta, monto, tipo, cd);
    }
    
    // New method to obtain account by number
    public CuentaModel obtenerCuentaPorNumero(String cuenta) {
        WSCuenta_Service servicio = new WSCuenta_Service();
        WSCuenta port = servicio.getWSCuentaPort();
        return port.obtenerCuentaPorNumero(cuenta);
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