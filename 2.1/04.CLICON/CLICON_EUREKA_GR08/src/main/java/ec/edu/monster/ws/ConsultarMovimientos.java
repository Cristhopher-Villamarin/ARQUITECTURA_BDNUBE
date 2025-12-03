
package ec.edu.monster.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for consultarMovimientos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
     * Gets the value of the numcuenta property.
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
     * Sets the value of the numcuenta property.
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
