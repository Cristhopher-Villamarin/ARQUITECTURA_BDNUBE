package ec.edu.monster.model;

public class MovimientoModel {
    private int numeroMovimiento;
    private String fechaMovimiento;
    private String codigoTipoMovimiento;
    private String tipoDescripcion;
    private double importeMovimiento;
    private String cuentaReferencia;
    private double saldo;

    public MovimientoModel() {
    }

    public MovimientoModel(int numeroMovimiento, String fechaMovimiento, String codigoTipoMovimiento,
                           String tipoDescripcion, double importeMovimiento, String cuentaReferencia, double saldo) {
        this.numeroMovimiento = numeroMovimiento;
        this.fechaMovimiento = fechaMovimiento;
        this.codigoTipoMovimiento = codigoTipoMovimiento;
        this.tipoDescripcion = tipoDescripcion;
        this.importeMovimiento = importeMovimiento;
        this.cuentaReferencia = cuentaReferencia;
        this.saldo = saldo;
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

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
