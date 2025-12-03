package ec.edu.monster.ws;

import ec.edu.monster.modelo.MovimientoModel;
import ec.edu.monster.servicio.MovimientoService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService(serviceName = "WSMovimiento")
public class WSMovimiento {

    @WebMethod(operationName = "consultarMovimientos")
    public List<MovimientoModel> consultarMovimientos(@WebParam(name = "numcuenta") String numcuenta) {
        MovimientoService servicio = new MovimientoService();
        try {
            return servicio.ObtenerMovimiento(numcuenta);
        } catch (SQLException ex) {
            Logger.getLogger(WSMovimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
