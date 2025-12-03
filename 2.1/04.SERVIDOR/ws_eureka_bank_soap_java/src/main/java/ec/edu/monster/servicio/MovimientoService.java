package ec.edu.monster.servicio;

import ec.edu.monster.Controlador.MovimientoDAO;
import ec.edu.monster.modelo.MovimientoModel;
import java.sql.SQLException;
import java.util.List;

public class MovimientoService {

    MovimientoDAO DAO = new MovimientoDAO();

    public List<MovimientoModel> ObtenerMovimiento(String cuenta) throws SQLException {
        return DAO.obtenerMovimientosPorCuenta(cuenta);
    }
}
