namespace CLIWEB_EUREKA_GR08.ec.edu.monster.model
{
    public class MovimientoModel
    {
        public string CodigoCuenta { get; set; }
        public int NumeroMovimiento { get; set; }
        public string FechaMovimiento { get; set; }
        public string CodigoEmpleado { get; set; }
        public string CodigoTipoMovimiento { get; set; }
        public string TipoDescripcion { get; set; }
        public double ImporteMovimiento { get; set; }
        public string CuentaReferencia { get; set; }
        public double Saldo { get; set; }
    }
}

