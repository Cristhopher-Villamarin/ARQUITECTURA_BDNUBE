package ec.edu.monster.ws;

import ec.edu.monster.modelo.CuentaModel;
import ec.edu.monster.servicio.CuentaService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.sql.SQLException;

@WebService(serviceName = "WSCuenta")
public class WSCuenta {

    @WebMethod(operationName = "cuenta")
    public boolean deposito(@WebParam(name = "cuenta") String cuenta, @WebParam(name = "monto") String monto, @WebParam(name = "tipo") String tipo, @WebParam(name = "cd") String cd) {
        CuentaService servicio = new CuentaService();
        return servicio.actualizarSaldoYRegistrarMovimiento(cuenta, monto, tipo, cd);
    }

    @WebMethod(operationName = "obtenerCuentaPorNumero")
    public CuentaModel obtenerCuentaPorNumero(@WebParam(name = "cuenta") String cuenta) {
        try {
            CuentaService servicio = new CuentaService();
            return servicio.obtenerCuenta(cuenta);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
