using ec.edu.monster.db;
using ec.edu.monster.model;
using Microsoft.Data.SqlClient;

namespace ec.edu.monster.dao;

public class MovimientoDAO
{
    private readonly IConfiguration _configuration;

    public MovimientoDAO(IConfiguration configuration)
    {
        _configuration = configuration;
    }

    // TABLAS: MOVIMIENTO Y TIPOMOVIMIENTO
    public List<MovimientoModel> ObtenerMovimientosPorCuenta(string codigoCuenta)
    {
        string sql = "SELECT m.chr_cuencodigo, m.int_movinumero, m.dtt_movifecha, " +
                    "m.chr_emplcodigo, m.chr_tipocodigo, m.dec_moviimporte, m.chr_cuenreferencia, " +
                    "t.vch_tipodescripcion AS tipoDescripcion, t.vch_tipoaccion AS tipoAccion " +
                    "FROM movimiento AS m " +
                    "INNER JOIN tipomovimiento AS t ON t.chr_tipocodigo = m.chr_tipocodigo " +
                    "WHERE m.chr_cuencodigo = @codigo " +
                    "ORDER BY m.dtt_movifecha ASC, m.int_movinumero ASC";

        var movimientosConSaldo = new List<MovimientoModel>();
        double saldoAcumulado = 0.0;

        using (var connection = AccesoBD.GetConnection(_configuration))
        {
            connection.Open();
            using (var command = new SqlCommand(sql, connection))
            {
                command.Parameters.AddWithValue("@codigo", codigoCuenta);
                
                using (var reader = command.ExecuteReader())
                {
                    // Obtener índices de columnas una sola vez
                    int idxCuencodigo = reader.GetOrdinal("chr_cuencodigo");
                    int idxMovinumero = reader.GetOrdinal("int_movinumero");
                    int idxMovifecha = reader.GetOrdinal("dtt_movifecha");
                    int idxEmplcodigo = reader.GetOrdinal("chr_emplcodigo");
                    int idxTipocodigo = reader.GetOrdinal("chr_tipocodigo");
                    int idxMoviimporte = reader.GetOrdinal("dec_moviimporte");
                    int idxCuenreferencia = reader.GetOrdinal("chr_cuenreferencia");
                    int idxTipoDescripcion = reader.GetOrdinal("tipoDescripcion");
                    int idxTipoAccion = reader.GetOrdinal("tipoAccion");

                    while (reader.Read())
                    {
                        var movimiento = new MovimientoModel();
                        
                        if (!reader.IsDBNull(idxMovifecha))
                        {
                            movimiento.FechaMovimiento = reader.GetDateTime(idxMovifecha).ToString("yyyy-MM-dd");
                        }
                        
                        movimiento.CodigoCuenta = reader.GetString(idxCuencodigo);
                        movimiento.NumeroMovimiento = reader.GetInt32(idxMovinumero);
                        movimiento.CodigoEmpleado = reader.IsDBNull(idxEmplcodigo) ? null : reader.GetString(idxEmplcodigo);
                        movimiento.CodigoTipoMovimiento = reader.GetString(idxTipocodigo);
                        movimiento.TipoDescripcion = reader.IsDBNull(idxTipoDescripcion) ? null : reader.GetString(idxTipoDescripcion);
                        movimiento.ImporteMovimiento = Convert.ToDouble(reader.GetDecimal(idxMoviimporte));
                        movimiento.CuentaReferencia = reader.IsDBNull(idxCuenreferencia) ? null : reader.GetString(idxCuenreferencia);

                        // Calcular el saldo acumulativo
                        string? tipoAccion = reader.IsDBNull(idxTipoAccion) ? null : reader.GetString(idxTipoAccion);
                        double importe = movimiento.ImporteMovimiento;

                        if ("INGRESO".Equals(tipoAccion))
                        {
                            saldoAcumulado += importe;
                        }
                        else if ("SALIDA".Equals(tipoAccion))
                        {
                            saldoAcumulado -= importe;
                        }

                        movimiento.Saldo = saldoAcumulado;
                        movimientosConSaldo.Add(movimiento);
                    }
                }
            }
        }

        // Ordenar por fecha descendente (más reciente primero) y luego por número de movimiento
        movimientosConSaldo = movimientosConSaldo
            .OrderByDescending(m => m.FechaMovimiento)
            .ThenByDescending(m => m.NumeroMovimiento)
            .ToList();

        return movimientosConSaldo;
    }

    // Método para registrar un movimiento
    public void RegistrarMovimiento(MovimientoModel movimiento)
    {
        string sql = "INSERT INTO movimiento (chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, " +
                    "chr_tipocodigo, dec_moviimporte, chr_cuenreferencia) VALUES (@codigoCuenta, @numeroMovimiento, @fechaMovimiento, @codigoEmpleado, @codigoTipoMovimiento, @importeMovimiento, @cuentaReferencia)";
        
        using (var connection = AccesoBD.GetConnection(_configuration))
        {
            connection.Open();
            using (var command = new SqlCommand(sql, connection))
            {
                command.Parameters.AddWithValue("@codigoCuenta", movimiento.CodigoCuenta);
                command.Parameters.AddWithValue("@numeroMovimiento", movimiento.NumeroMovimiento);
                command.Parameters.AddWithValue("@fechaMovimiento", DateTime.Parse(movimiento.FechaMovimiento ?? DateTime.Now.ToString("yyyy-MM-dd")));
                command.Parameters.AddWithValue("@codigoEmpleado", movimiento.CodigoEmpleado);
                command.Parameters.AddWithValue("@codigoTipoMovimiento", movimiento.CodigoTipoMovimiento);
                command.Parameters.AddWithValue("@importeMovimiento", movimiento.ImporteMovimiento);
                command.Parameters.AddWithValue("@cuentaReferencia", movimiento.CuentaReferencia ?? (object)DBNull.Value);
                
                command.ExecuteNonQuery();
            }
        }
    }
}

