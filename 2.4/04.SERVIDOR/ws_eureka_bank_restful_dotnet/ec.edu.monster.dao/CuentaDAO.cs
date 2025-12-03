using ec.edu.monster.db;
using ec.edu.monster.model;
using Microsoft.Data.SqlClient;

namespace ec.edu.monster.dao;

public class CuentaDAO
{
    private readonly IConfiguration _configuration;

    public CuentaDAO(IConfiguration configuration)
    {
        _configuration = configuration;
    }

    // Actualizar el saldo de la cuenta cuando se hace un Movimiento (Retiro, Deposito, Transferencia)
    public bool ActualizarSaldoCuenta(string codigoCuenta, string valorMovimiento)
    {
        string sql = "UPDATE cuenta " +
                    "SET dec_cuensaldo = dec_cuensaldo + @valor, " +
                    "int_cuencontmov = int_cuencontmov + 1 " +
                    "WHERE chr_cuencodigo = @codigo";
        
        using (var connection = AccesoBD.GetConnection(_configuration))
        {
            connection.Open();
            using (var command = new SqlCommand(sql, connection))
            {
                command.Parameters.AddWithValue("@valor", double.Parse(valorMovimiento));
                command.Parameters.AddWithValue("@codigo", codigoCuenta);

                int rowsUpdated = command.ExecuteNonQuery();
                return rowsUpdated > 0;
            }
        }
    }

    // Método para obtener una cuenta por su Código
    public CuentaModel? ObtenerCuentaPorCodigo(string codigoCuenta)
    {
        string sql = "SELECT * FROM cuenta WHERE chr_cuencodigo = @codigo";
        
        using (var connection = AccesoBD.GetConnection(_configuration))
        {
            connection.Open();
            using (var command = new SqlCommand(sql, connection))
            {
                command.Parameters.AddWithValue("@codigo", codigoCuenta);
                
                using (var reader = command.ExecuteReader())
                {
                    if (reader.Read())
                    {
                        int idxCuenCodigo = reader.GetOrdinal("chr_cuencodigo");
                        int idxMoneCodigo = reader.GetOrdinal("chr_monecodigo");
                        int idxSucucodigo = reader.GetOrdinal("chr_sucucodigo");
                        int idxEmplCreaCuenta = reader.GetOrdinal("chr_emplcreacuenta");
                        int idxClieCodigo = reader.GetOrdinal("chr_cliecodigo");
                        int idxCuenSaldo = reader.GetOrdinal("dec_cuensaldo");
                        int idxCuenFechaCreacion = reader.GetOrdinal("dtt_cuenfechacreacion");
                        int idxCuenEstado = reader.GetOrdinal("vch_cuenestado");
                        int idxCuenContMov = reader.GetOrdinal("int_cuencontmov");
                        int idxCuenClave = reader.GetOrdinal("chr_cuenclave");

                        var cuenta = new CuentaModel
                        {
                            ChrCuenCodigo = reader.GetString(idxCuenCodigo),
                            ChrMoneCodigo = reader.IsDBNull(idxMoneCodigo) ? null : reader.GetString(idxMoneCodigo),
                            ChrSucucodigo = reader.IsDBNull(idxSucucodigo) ? null : reader.GetString(idxSucucodigo),
                            ChrEmplCreaCuenta = reader.IsDBNull(idxEmplCreaCuenta) ? null : reader.GetString(idxEmplCreaCuenta),
                            ChrClieCodigo = reader.IsDBNull(idxClieCodigo) ? null : reader.GetString(idxClieCodigo),
                            DecCuenSaldo = Convert.ToDouble(reader.GetDecimal(idxCuenSaldo)),
                            DttCuenFechaCreacion = reader.IsDBNull(idxCuenFechaCreacion) ? null : reader.GetDateTime(idxCuenFechaCreacion),
                            VchCuenEstado = reader.IsDBNull(idxCuenEstado) ? null : reader.GetString(idxCuenEstado),
                            IntCuenContMov = reader.GetInt32(idxCuenContMov),
                            ChrCuenClave = reader.IsDBNull(idxCuenClave) ? null : reader.GetString(idxCuenClave)
                        };
                        return cuenta;
                    }
                }
            }
        }
        return null;
    }
}

