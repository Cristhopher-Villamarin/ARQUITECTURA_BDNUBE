using ec.edu.monster.dao;
using ec.edu.monster.model;
using ec.edu.monster.servicio;
using Microsoft.AspNetCore.Mvc;

namespace ws_eureka_bank_restful_dotnet.Controllers;

[ApiController]
[Route("api/[controller]")]
public class CuentaController : ControllerBase
{
    private readonly IConfiguration _configuration;

    public CuentaController(IConfiguration configuration)
    {
        _configuration = configuration;
    }

    // Endpoint para Depósito
    [HttpPost("deposito")]
    [Consumes("application/json")]
    [Produces("application/json")]
    public IActionResult Deposito([FromBody] DepositoRequest request)
    {
        if (request == null || string.IsNullOrEmpty(request.Cuenta) || request.Monto <= 0)
        {
            return BadRequest(new OperacionResponse(false, 0, "Datos inválidos para el depósito"));
        }

        try
        {
            var servicio = new CuentaService(_configuration);
            bool success = servicio.ActualizarSaldoYRegistrarMovimiento(
                request.Cuenta,
                request.Monto.ToString(),
                "DEP",
                null
            );

            // Obtener el saldo actual después de la operación
            var cuentaDAO = new CuentaDAO(_configuration);
            var cuenta = cuentaDAO.ObtenerCuentaPorCodigo(request.Cuenta);
            double saldo = cuenta != null ? cuenta.DecCuenSaldo : 0.0;

            string mensaje = success ? "Depósito realizado exitosamente" : "Error al realizar el depósito";

            return Ok(new OperacionResponse(success, saldo, mensaje));
        }
        catch (Exception ex)
        {
            return StatusCode(500, new OperacionResponse(false, 0, $"Error interno: {ex.Message}"));
        }
    }

    // Endpoint para Retiro
    [HttpPost("retiro")]
    [Consumes("application/json")]
    [Produces("application/json")]
    public IActionResult Retiro([FromBody] RetiroRequest request)
    {
        if (request == null || string.IsNullOrEmpty(request.Cuenta) || request.Monto <= 0)
        {
            return BadRequest(new OperacionResponse(false, 0, "Datos inválidos para el retiro"));
        }

        try
        {
            var servicio = new CuentaService(_configuration);
            bool success = servicio.ActualizarSaldoYRegistrarMovimiento(
                request.Cuenta,
                request.Monto.ToString(),
                "RET",
                null
            );

            // Obtener el saldo actual después de la operación
            var cuentaDAO = new CuentaDAO(_configuration);
            var cuenta = cuentaDAO.ObtenerCuentaPorCodigo(request.Cuenta);
            double saldo = cuenta != null ? cuenta.DecCuenSaldo : 0.0;

            string mensaje;
            if (success)
            {
                mensaje = "Retiro realizado exitosamente";
            }
            else if (cuenta != null)
            {
                mensaje = "Saldo insuficiente";
            }
            else
            {
                mensaje = "Error al realizar el retiro";
            }

            return Ok(new OperacionResponse(success, saldo, mensaje));
        }
        catch (Exception ex)
        {
            return StatusCode(500, new OperacionResponse(false, 0, $"Error interno: {ex.Message}"));
        }
    }

    // Endpoint para Transferencia
    [HttpPost("transferencia")]
    [Consumes("application/json")]
    [Produces("application/json")]
    public IActionResult Transferencia([FromBody] TransferenciaRequest request)
    {
        if (request == null || string.IsNullOrEmpty(request.Cuenta) || 
            string.IsNullOrEmpty(request.CuentaDestino) || request.Monto <= 0)
        {
            return BadRequest(new OperacionResponse(false, 0, "Datos inválidos para la transferencia"));
        }

        if (request.Cuenta == request.CuentaDestino)
        {
            return BadRequest(new OperacionResponse(false, 0, "La cuenta origen y destino no pueden ser iguales"));
        }

        try
        {
            var servicio = new CuentaService(_configuration);
            bool success = servicio.ActualizarSaldoYRegistrarMovimiento(
                request.Cuenta,
                request.Monto.ToString(),
                "TRA",
                request.CuentaDestino
            );

            // Obtener el saldo actual después de la operación
            var cuentaDAO = new CuentaDAO(_configuration);
            var cuenta = cuentaDAO.ObtenerCuentaPorCodigo(request.Cuenta);
            double saldo = cuenta != null ? cuenta.DecCuenSaldo : 0.0;

            string mensaje;
            if (success)
            {
                mensaje = "Transferencia realizada exitosamente";
            }
            else if (cuenta != null)
            {
                mensaje = "Saldo insuficiente o cuenta destino inválida";
            }
            else
            {
                mensaje = "Error al realizar la transferencia";
            }

            return Ok(new OperacionResponse(success, saldo, mensaje));
        }
        catch (Exception ex)
        {
            return StatusCode(500, new OperacionResponse(false, 0, $"Error interno: {ex.Message}"));
        }
    }

    // Endpoint para consultar información de cuenta
    [HttpGet("{codigoCuenta}")]
    [Produces("application/json")]
    public IActionResult ObtenerCuenta(string codigoCuenta)
    {
        if (string.IsNullOrEmpty(codigoCuenta))
        {
            return BadRequest(new { message = "Código de cuenta es requerido" });
        }

        try
        {
            var servicio = new CuentaService(_configuration);
            var cuenta = servicio.ObtenerCuenta(codigoCuenta);

            if (cuenta == null)
            {
                return NotFound(new { message = "Cuenta no encontrada" });
            }

            return Ok(cuenta);
        }
        catch (Exception ex)
        {
            return StatusCode(500, new { message = $"Error interno: {ex.Message}" });
        }
    }
}

