
package ec.edu.monster.modelo;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

public class MovimientoModel {
      
    private String codigoCuenta;
    private int numeroMovimiento;
   // @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private String fechaMovimiento; // Changed to LocalDate
    private String codigoEmpleado;
    private String codigoTipoMovimiento;
    private String tipoDescripcion;
    private double importeMovimiento;
    private String cuentaReferencia;

    // Getters and setters
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

    public String getFechaMovimiento() { // Changed getter
        return fechaMovimiento;
    }

    public void setFechaMovimiento(String fechaMovimiento) { // Changed setter
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

    public double getImporteMovimiento() {
        return importeMovimiento;
    }

    public void setImporteMovimiento(double importeMovimiento) {
        this.importeMovimiento = importeMovimiento;
    }

    public String getCuentaReferencia() {
        return cuentaReferencia;
    }

    public String getTipoDescripcion() {
        return tipoDescripcion;
    }

    public void setTipoDescripcion(String tipoDescripcion) {
        this.tipoDescripcion = tipoDescripcion;
    }

    public void setCuentaReferencia(String cuentaReferencia) {
        this.cuentaReferencia = cuentaReferencia;
    }
}
