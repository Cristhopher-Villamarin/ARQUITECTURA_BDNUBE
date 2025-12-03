using ec.edu.monster.dao;
using ec.edu.monster.model;

namespace ec.edu.monster.servicio;

public class MovimientoService
{
    private readonly MovimientoDAO _dao;

    public MovimientoService(IConfiguration configuration)
    {
        _dao = new MovimientoDAO(configuration);
    }

    public List<MovimientoModel> ObtenerMovimiento(string cuenta)
    {
        return _dao.ObtenerMovimientosPorCuenta(cuenta);
    }
}

