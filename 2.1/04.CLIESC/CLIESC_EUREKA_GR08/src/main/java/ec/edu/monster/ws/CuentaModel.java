
package ec.edu.monster.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para cuentaModel complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
     * Obtiene el valor de la propiedad chrClieCodigo.
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
     * Define el valor de la propiedad chrClieCodigo.
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
     * Obtiene el valor de la propiedad chrCuenClave.
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
     * Define el valor de la propiedad chrCuenClave.
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
     * Obtiene el valor de la propiedad chrCuenCodigo.
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
     * Define el valor de la propiedad chrCuenCodigo.
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
     * Obtiene el valor de la propiedad chrEmplCreaCuenta.
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
     * Define el valor de la propiedad chrEmplCreaCuenta.
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
     * Obtiene el valor de la propiedad chrMoneCodigo.
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
     * Define el valor de la propiedad chrMoneCodigo.
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
     * Obtiene el valor de la propiedad chrSucucodigo.
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
     * Define el valor de la propiedad chrSucucodigo.
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
     * Obtiene el valor de la propiedad decCuenSaldo.
     * 
     */
    public double getDecCuenSaldo() {
        return decCuenSaldo;
    }

    /**
     * Define el valor de la propiedad decCuenSaldo.
     * 
     */
    public void setDecCuenSaldo(double value) {
        this.decCuenSaldo = value;
    }

    /**
     * Obtiene el valor de la propiedad dttCuenFechaCreacion.
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
     * Define el valor de la propiedad dttCuenFechaCreacion.
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
     * Obtiene el valor de la propiedad intCuenContMov.
     * 
     */
    public int getIntCuenContMov() {
        return intCuenContMov;
    }

    /**
     * Define el valor de la propiedad intCuenContMov.
     * 
     */
    public void setIntCuenContMov(int value) {
        this.intCuenContMov = value;
    }

    /**
     * Obtiene el valor de la propiedad vchCuenEstado.
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
     * Define el valor de la propiedad vchCuenEstado.
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
