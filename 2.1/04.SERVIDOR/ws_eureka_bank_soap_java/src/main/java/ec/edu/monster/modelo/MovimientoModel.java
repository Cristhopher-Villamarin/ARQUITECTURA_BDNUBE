package ec.edu.monster.modelo;

public class MovimientoModel {
    
    // Tabla Movimiento DB
    private String codigoCuenta;
    private int numeroMovimiento;
    private String fechaMovimiento; // Changed to LocalDate
    private String codigoEmpleado;
    private String codigoTipoMovimiento;
    private String tipoDescripcion;
    private double importeMovimiento;
    private String cuentaReferencia;
    private double saldo; // Saldo acumulativo después del movimiento

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    public String getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(String codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }

    public int getNumeroMovimiento() {
        return numeroMovimiento;
    }

    public void setNumeroMovimiento(int numeroMovimiento) {
        this.numeroMovimiento = numeroMovimiento;
    }

    public String getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(String fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getCodigoTipoMovimiento() {
        return codigoTipoMovimiento;
    }

    public void setCodigoTipoMovimiento(String codigoTipoMovimiento) {
        this.codigoTipoMovimiento = codigoTipoMovimiento;
    }

    public String getTipoDescripcion() {
        return tipoDescripcion;
    }

    public void setTipoDescripcion(String tipoDescripcion) {
        this.tipoDescripcion = tipoDescripcion;
    }

    public double getImporteMovimiento() {
        return importeMovimiento;
    }

    public void setImporteMovimiento(double importeMovimiento) {
        this.importeMovimiento = importeMovimiento;
    }

    public String getCuentaReferencia() {
        return cuentaReferencia;
    }

    public void setCuentaReferencia(String cuentaReferencia) {
        this.cuentaReferencia = cuentaReferencia;
    }
}
