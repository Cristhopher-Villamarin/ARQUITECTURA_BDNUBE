package ec.edu.monster.service;
import ec.edu.monster.ws.MovimientoModel;
import ec.edu.monster.ws.WSMovimiento;
import ec.edu.monster.ws.WSMovimiento_Service;
import java.util.List;

public class MovimientoService {
    
    public List<MovimientoModel> obtenerMovimientos (String cuenta){
        WSMovimiento_Service servicio = new WSMovimiento_Service();
        WSMovimiento port = servicio.getWSMovimientoPort();
        return port.consultarMovimientos(cuenta);
    }
}
