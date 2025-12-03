using ec.edu.monster.dao;
using ec.edu.monster.model;

namespace ec.edu.monster.servicio;

public class CuentaService
{
    private readonly MovimientoDAO _movimientoDAO;
    private readonly CuentaDAO _cuentaDAO;

    public CuentaService(IConfiguration configuration)
    {
        _movimientoDAO = new MovimientoDAO(configuration);
        _cuentaDAO = new CuentaDAO(configuration);
    }

    // Método para actualizar el saldo de la cuenta y registrar el movimiento
    public bool ActualizarSaldoYRegistrarMovimiento(string codigoCuenta, string valorMovimiento, string tipo, string? cuentaDest)
    {
        try
        {
            double importe = double.Parse(valorMovimiento);
            string codTipo = "";

            // Validate the operation type
            if (tipo.Equals("RET", StringComparison.OrdinalIgnoreCase))  // Retiro
            {
                codTipo = "004";

                var cuenta = _cuentaDAO.ObtenerCuentaPorCodigo(codigoCuenta);
                if (cuenta == null || cuenta.DecCuenSaldo < importe)
                {
                    return false;
                }

                if (!_cuentaDAO.ActualizarSaldoCuenta(codigoCuenta, (-importe).ToString()))
                {
                    return false;  // Return false if balance couldn't be updated
                }
            }
            else if (tipo.Equals("DEP", StringComparison.OrdinalIgnoreCase))
            {
                codTipo = "003";
                if (!_cuentaDAO.ActualizarSaldoCuenta(codigoCuenta, valorMovimiento))
                {
                    return false;  // Return false if balance couldn't be updated
                }
            }
            else if (tipo.Equals("TRA", StringComparison.OrdinalIgnoreCase))  // Transferencia
            {
                // Check that the destination account is provided
                codTipo = "009";
                if (string.IsNullOrEmpty(cuentaDest))
                {
                    throw new ArgumentException("Cuenta destino es obligatoria para transferencias.");
                }

                var cuentaOrigen = _cuentaDAO.ObtenerCuentaPorCodigo(codigoCuenta);
                if (cuentaOrigen == null || cuentaOrigen.DecCuenSaldo < importe)
                {
                    return false;
                }

                // Deduct from source account
                if (!_cuentaDAO.ActualizarSaldoCuenta(codigoCuenta, (-importe).ToString()))
                {
                    return false;  // Return false if balance couldn't be updated
                }

                // Add to destination account
                if (!_cuentaDAO.ActualizarSaldoCuenta(cuentaDest, valorMovimiento))
                {
                    // Rollback source account update if destination update fails
                    _cuentaDAO.ActualizarSaldoCuenta(codigoCuenta, importe.ToString());
                    return false;
                }
            }
            else
            {
                throw new ArgumentException($"Tipo de movimiento no soportado: {tipo}");
            }

            // Register the movement
            int numeroMovimiento = ObtenerSiguienteNumeroMovimiento(codigoCuenta);

            var movimiento = new MovimientoModel
            {
                CodigoCuenta = codigoCuenta,
                NumeroMovimiento = numeroMovimiento,
                FechaMovimiento = DateTime.Now.ToString("yyyy-MM-dd"),
                CodigoEmpleado = "0001",  // Update as needed
                CodigoTipoMovimiento = codTipo,
                ImporteMovimiento = importe
            };

            _movimientoDAO.RegistrarMovimiento(movimiento);

            // Register the movement for the destination account in case of transfer
            if (tipo.Equals("TRA", StringComparison.OrdinalIgnoreCase))
            {
                int numeroMovimientoDest = ObtenerSiguienteNumeroMovimiento(cuentaDest!);

                var movimientoDest = new MovimientoModel
                {
                    CodigoCuenta = cuentaDest,
                    NumeroMovimiento = numeroMovimientoDest,
                    FechaMovimiento = DateTime.Now.ToString("yyyy-MM-dd"),
                    CodigoEmpleado = "0001",  // Update as needed
                    CodigoTipoMovimiento = "008",
                    ImporteMovimiento = importe
                };

                _movimientoDAO.RegistrarMovimiento(movimientoDest);
            }

            return true;
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error en ActualizarSaldoYRegistrarMovimiento: {ex.Message}");
            return false;
        }
    }

    // Método para obtener el siguiente número de movimiento para una cuenta
    private int ObtenerSiguienteNumeroMovimiento(string codigoCuenta)
    {
        var movimientos = _movimientoDAO.ObtenerMovimientosPorCuenta(codigoCuenta);
        int numeroMovimiento = 1;  // Empezamos con el número de movimiento 1 por defecto

        // Buscamos el último número de movimiento
        foreach (var movimiento in movimientos)
        {
            if (movimiento.NumeroMovimiento >= numeroMovimiento)
            {
                numeroMovimiento = movimiento.NumeroMovimiento + 1;
            }
        }
        return numeroMovimiento;
    }

    public CuentaModel? ObtenerCuenta(string codigoCuenta)
    {
        return _cuentaDAO.ObtenerCuentaPorCodigo(codigoCuenta);
    }
}

