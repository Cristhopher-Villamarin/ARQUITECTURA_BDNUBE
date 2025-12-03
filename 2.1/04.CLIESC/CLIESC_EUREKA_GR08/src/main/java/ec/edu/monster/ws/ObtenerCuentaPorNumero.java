
package ec.edu.monster.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para obtenerCuentaPorNumero complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="obtenerCuentaPorNumero">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="cuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerCuentaPorNumero", propOrder = {
    "cuenta"
})
public class ObtenerCuentaPorNumero {

    protected String cuenta;

    /**
     * Obtiene el valor de la propiedad cuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuenta() {
        return cuenta;
    }

    /**
     * Define el valor de la propiedad cuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuenta(String value) {
        this.cuenta = value;
    }

}
