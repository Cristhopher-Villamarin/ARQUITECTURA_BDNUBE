
package ec.edu.monster.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cuentaModel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="cuentaModel">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="chrClieCodigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="chrCuenClave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="chrCuenCodigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="chrEmplCreaCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="chrMoneCodigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="chrSucucodigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="decCuenSaldo" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         <element name="dttCuenFechaCreacion" type="{http://ws.monster.edu.ec/}date" minOccurs="0"/>
 *         <element name="intCuenContMov" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="vchCuenEstado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cuentaModel", propOrder = {
    "chrClieCodigo",
    "chrCuenClave",
    "chrCuenCodigo",
    "chrEmplCreaCuenta",
    "chrMoneCodigo",
    "chrSucucodigo",
    "decCuenSaldo",
    "dttCuenFechaCreacion",
    "intCuenContMov",
    "vchCuenEstado"
})
public class CuentaModel {

    protected String chrClieCodigo;
    protected String chrCuenClave;
    protected String chrCuenCodigo;
    protected String chrEmplCreaCuenta;
    protected String chrMoneCodigo;
    protected String chrSucucodigo;
    protected double decCuenSaldo;
    protected Date dttCuenFechaCreacion;
    protected int intCuenContMov;
    protected String vchCuenEstado;

    /**
     * Gets the value of the chrClieCodigo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChrClieCodigo() {
        return chrClieCodigo;
    }

    /**
     * Sets the value of the chrClieCodigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChrClieCodigo(String value) {
        this.chrClieCodigo = value;
    }

    /**
     * Gets the value of the chrCuenClave property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChrCuenClave() {
        return chrCuenClave;
    }

    /**
     * Sets the value of the chrCuenClave property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChrCuenClave(String value) {
        this.chrCuenClave = value;
    }

    /**
     * Gets the value of the chrCuenCodigo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChrCuenCodigo() {
        return chrCuenCodigo;
    }

    /**
     * Sets the value of the chrCuenCodigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChrCuenCodigo(String value) {
        this.chrCuenCodigo = value;
    }

    /**
     * Gets the value of the chrEmplCreaCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChrEmplCreaCuenta() {
        return chrEmplCreaCuenta;
    }

    /**
     * Sets the value of the chrEmplCreaCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChrEmplCreaCuenta(String value) {
        this.chrEmplCreaCuenta = value;
    }

    /**
     * Gets the value of the chrMoneCodigo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChrMoneCodigo() {
        return chrMoneCodigo;
    }

    /**
     * Sets the value of the chrMoneCodigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChrMoneCodigo(String value) {
        this.chrMoneCodigo = value;
    }

    /**
     * Gets the value of the chrSucucodigo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChrSucucodigo() {
        return chrSucucodigo;
    }

    /**
     * Sets the value of the chrSucucodigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChrSucucodigo(String value) {
        this.chrSucucodigo = value;
    }

    /**
     * Gets the value of the decCuenSaldo property.
     * 
     */
    public double getDecCuenSaldo() {
        return decCuenSaldo;
    }

    /**
     * Sets the value of the decCuenSaldo property.
     * 
     */
    public void setDecCuenSaldo(double value) {
        this.decCuenSaldo = value;
    }

    /**
     * Gets the value of the dttCuenFechaCreacion property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getDttCuenFechaCreacion() {
        return dttCuenFechaCreacion;
    }

    /**
     * Sets the value of the dttCuenFechaCreacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setDttCuenFechaCreacion(Date value) {
        this.dttCuenFechaCreacion = value;
    }

    /**
     * Gets the value of the intCuenContMov property.
     * 
     */
    public int getIntCuenContMov() {
        return intCuenContMov;
    }

    /**
     * Sets the value of the intCuenContMov property.
     * 
     */
    public void setIntCuenContMov(int value) {
        this.intCuenContMov = value;
    }

    /**
     * Gets the value of the vchCuenEstado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVchCuenEstado() {
        return vchCuenEstado;
    }

    /**
     * Sets the value of the vchCuenEstado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVchCuenEstado(String value) {
        this.vchCuenEstado = value;
    }

}
