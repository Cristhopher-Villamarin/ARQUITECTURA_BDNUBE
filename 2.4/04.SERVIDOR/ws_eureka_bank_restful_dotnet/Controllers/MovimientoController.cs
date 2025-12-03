using ec.edu.monster.model;
using ec.edu.monster.servicio;
using Microsoft.AspNetCore.Mvc;

namespace ws_eureka_bank_restful_dotnet.Controllers;

[ApiController]
[Route("api/[controller]")]
public class MovimientoController : ControllerBase
{
    private readonly IConfiguration _configuration;

    public MovimientoController(IConfiguration configuration)
    {
        _configuration = configuration;
    }

    [HttpPost]
    [Consumes("application/json")]
    [Produces("application/json")]
    public IActionResult GetMovimientos([FromBody] MovimientoRequest request)
    {
        if (request == null || string.IsNullOrEmpty(request.Numcuenta))
        {
            return BadRequest(new { message = "Número de cuenta es requerido" });
        }

        try
        {
            var servicio = new MovimientoService(_configuration);
            var movimientos = servicio.ObtenerMovimiento(request.Numcuenta);

            return Ok(movimientos);
        }
        catch (Exception ex)
        {
            return StatusCode(500, new { message = $"Error interno: {ex.Message}" });
        }
    }
}

