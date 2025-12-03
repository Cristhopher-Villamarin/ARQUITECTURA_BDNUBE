
package ec.edu.monster.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para movimientoModel complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
     * Obtiene el valor de la propiedad codigoCuenta.
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
     * Define el valor de la propiedad codigoCuenta.
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
     * Obtiene el valor de la propiedad codigoEmpleado.
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
     * Define el valor de la propiedad codigoEmpleado.
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
     * Obtiene el valor de la propiedad codigoTipoMovimiento.
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
     * Define el valor de la propiedad codigoTipoMovimiento.
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
     * Obtiene el valor de la propiedad cuentaReferencia.
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
     * Define el valor de la propiedad cuentaReferencia.
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
     * Obtiene el valor de la propiedad fechaMovimiento.
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
     * Define el valor de la propiedad fechaMovimiento.
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
     * Obtiene el valor de la propiedad importeMovimiento.
     * 
     */
    public double getImporteMovimiento() {
        return importeMovimiento;
    }

    /**
     * Define el valor de la propiedad importeMovimiento.
     * 
     */
    public void setImporteMovimiento(double value) {
        this.importeMovimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroMovimiento.
     * 
     */
    public int getNumeroMovimiento() {
        return numeroMovimiento;
    }

    /**
     * Define el valor de la propiedad numeroMovimiento.
     * 
     */
    public void setNumeroMovimiento(int value) {
        this.numeroMovimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad saldo.
     * 
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Define el valor de la propiedad saldo.
     * 
     */
    public void setSaldo(double value) {
        this.saldo = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoDescripcion.
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
     * Define el valor de la propiedad tipoDescripcion.
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
