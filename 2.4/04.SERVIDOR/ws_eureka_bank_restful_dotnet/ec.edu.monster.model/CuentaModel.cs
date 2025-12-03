namespace ec.edu.monster.model;

public class CuentaModel
{
    // Tabla Cuenta DB
    public string? ChrCuenCodigo { get; set; }       // Código de la cuenta
    public string? ChrMoneCodigo { get; set; }       // Código de moneda
    public string? ChrSucucodigo { get; set; }       // Código de sucursal
    public string? ChrEmplCreaCuenta { get; set; }   // Código de empleado que crea la cuenta
    public string? ChrClieCodigo { get; set; }       // Código de cliente
    public double DecCuenSaldo { get; set; }         // Saldo de la cuenta
    public DateTime? DttCuenFechaCreacion { get; set; }  // Fecha de creación de la cuenta
    public string? VchCuenEstado { get; set; }       // Estado de la cuenta (ACTIVO, ANULADO, CANCELADO)
    public int IntCuenContMov { get; set; }          // Contador de movimientos de la cuenta
    public string? ChrCuenClave { get; set; }        // Clave de la cuenta
}

