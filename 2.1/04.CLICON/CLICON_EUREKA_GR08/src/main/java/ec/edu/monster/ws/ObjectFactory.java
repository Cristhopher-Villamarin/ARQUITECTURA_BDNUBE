
package ec.edu.monster.ws;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ec.edu.monster.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private static final QName _ConsultarMovimientos_QNAME = new QName("http://ws.monster.edu.ec/", "consultarMovimientos");
    private static final QName _ConsultarMovimientosResponse_QNAME = new QName("http://ws.monster.edu.ec/", "consultarMovimientosResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.edu.monster.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultarMovimientos }
     * 
     * @return
     *     the new instance of {@link ConsultarMovimientos }
     */
    public ConsultarMovimientos createConsultarMovimientos() {
        return new ConsultarMovimientos();
    }

    /**
     * Create an instance of {@link ConsultarMovimientosResponse }
     * 
     * @return
     *     the new instance of {@link ConsultarMovimientosResponse }
     */
    public ConsultarMovimientosResponse createConsultarMovimientosResponse() {
        return new ConsultarMovimientosResponse();
    }

    /**
     * Create an instance of {@link MovimientoModel }
     * 
     * @return
     *     the new instance of {@link MovimientoModel }
     */
    public MovimientoModel createMovimientoModel() {
        return new MovimientoModel();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarMovimientos }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ConsultarMovimientos }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.monster.edu.ec/", name = "consultarMovimientos")
    public JAXBElement<ConsultarMovimientos> createConsultarMovimientos(ConsultarMovimientos value) {
        return new JAXBElement<>(_ConsultarMovimientos_QNAME, ConsultarMovimientos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarMovimientosResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ConsultarMovimientosResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.monster.edu.ec/", name = "consultarMovimientosResponse")
    public JAXBElement<ConsultarMovimientosResponse> createConsultarMovimientosResponse(ConsultarMovimientosResponse value) {
        return new JAXBElement<>(_ConsultarMovimientosResponse_QNAME, ConsultarMovimientosResponse.class, null, value);
    }

}
