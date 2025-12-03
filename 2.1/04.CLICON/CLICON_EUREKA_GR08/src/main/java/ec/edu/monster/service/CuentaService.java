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
}