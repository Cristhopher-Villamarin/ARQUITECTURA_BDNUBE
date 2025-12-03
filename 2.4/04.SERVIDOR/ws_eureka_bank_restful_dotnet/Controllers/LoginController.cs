using ec.edu.monster.model;
using ec.edu.monster.servicio;
using Microsoft.AspNetCore.Mvc;

namespace ws_eureka_bank_restful_dotnet.Controllers;

[ApiController]
[Route("api/[controller]")]
public class LoginController : ControllerBase
{
    [HttpPost]
    [Consumes("application/json")]
    [Produces("application/json")]
    public IActionResult Authenticate([FromBody] LoginRequest request)
    {
        if (request == null || string.IsNullOrEmpty(request.Username) || string.IsNullOrEmpty(request.Password))
        {
            return BadRequest(new { success = false, message = "Username y Password son requeridos" });
        }

        var service = new LoginService();
        bool isValid = service.Login(request.Username, request.Password);

        if (isValid)
        {
            return Ok(new { success = true, message = "Autenticación exitosa" });
        }
        else
        {
            return Unauthorized(new { success = false, message = "Credenciales inválidas" });
        }
    }
}

