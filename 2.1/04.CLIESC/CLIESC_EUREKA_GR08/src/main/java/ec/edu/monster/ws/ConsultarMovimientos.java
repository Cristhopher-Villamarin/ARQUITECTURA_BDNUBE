
package ec.edu.monster.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultarMovimientos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="consultarMovimientos">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="numcuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarMovimientos", propOrder = {
    "numcuenta"
})
public class ConsultarMovimientos {

    protected String numcuenta;

    /**
     * Obtiene el valor de la propiedad numcuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumcuenta() {
        return numcuenta;
    }

    /**
     * Define el valor de la propiedad numcuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumcuenta(String value) {
        this.numcuenta = value;
    }

}
