
package ec.edu.monster.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for movimientoModel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="movimientoModel">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="codigoCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="codigoEmpleado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="codigoTipoMovimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="cuentaReferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaMovimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="importeMovimiento" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         <element name="numeroMovimiento" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="saldo" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         <element name="tipoDescripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "movimientoModel", propOrder = {
    "codigoCuenta",
    "codigoEmpleado",
    "codigoTipoMovimiento",
    "cuentaReferencia",
    "fechaMovimiento",
    "importeMovimiento",
    "numeroMovimiento",
    "saldo",
    "tipoDescripcion"
})
public class MovimientoModel {

    protected String codigoCuenta;
    protected String codigoEmpleado;
    protected String codigoTipoMovimiento;
    protected String cuentaReferencia;
    protected String fechaMovimiento;
    protected double importeMovimiento;
    protected int numeroMovimiento;
    protected double saldo;
    protected String tipoDescripcion;

    /**
     * Gets the value of the codigoCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCuenta() {
        return codigoCuenta;
    }

    /**
     * Sets the value of the codigoCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCuenta(String value) {
        this.codigoCuenta = value;
    }

    /**
     * Gets the value of the codigoEmpleado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    /**
     * Sets the value of the codigoEmpleado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoEmpleado(String value) {
        this.codigoEmpleado = value;
    }

    /**
     * Gets the value of the codigoTipoMovimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoTipoMovimiento() {
        return codigoTipoMovimiento;
    }

    /**
     * Sets the value of the codigoTipoMovimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoTipoMovimiento(String value) {
        this.codigoTipoMovimiento = value;
    }

    /**
     * Gets the value of the cuentaReferencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuentaReferencia() {
        return cuentaReferencia;
    }

    /**
     * Sets the value of the cuentaReferencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuentaReferencia(String value) {
        this.cuentaReferencia = value;
    }

    /**
     * Gets the value of the fechaMovimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaMovimiento() {
        return fechaMovimiento;
    }

    /**
     * Sets the value of the fechaMovimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaMovimiento(String value) {
        this.fechaMovimiento = value;
    }

    /**
     * Gets the value of the importeMovimiento property.
     * 
     */
    public double getImporteMovimiento() {
        return importeMovimiento;
    }

    /**
     * Sets the value of the importeMovimiento property.
     * 
     */
    public void setImporteMovimiento(double value) {
        this.importeMovimiento = value;
    }

    /**
     * Gets the value of the numeroMovimiento property.
     * 
     */
    public int getNumeroMovimiento() {
        return numeroMovimiento;
    }

    /**
     * Sets the value of the numeroMovimiento property.
     * 
     */
    public void setNumeroMovimiento(int value) {
        this.numeroMovimiento = value;
    }

    /**
     * Gets the value of the saldo property.
     * 
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Sets the value of the saldo property.
     * 
     */
    public void setSaldo(double value) {
        this.saldo = value;
    }

    /**
     * Gets the value of the tipoDescripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDescripcion() {
        return tipoDescripcion;
    }

    /**
     * Sets the value of the tipoDescripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDescripcion(String value) {
        this.tipoDescripcion = value;
    }

}
