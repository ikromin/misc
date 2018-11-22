
package net.igorkromin.wsat.participant;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the net.igorkromin.wsat.participant package. 
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

    private final static QName _DoOpResponse_QNAME = new QName("http://igorkromin.net/wsat/participant", "doOpResponse");
    private final static QName _DoOp_QNAME = new QName("http://igorkromin.net/wsat/participant", "doOp");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: net.igorkromin.wsat.participant
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DoOp }
     * 
     */
    public DoOp createDoOp() {
        return new DoOp();
    }

    /**
     * Create an instance of {@link DoOpResponse }
     * 
     */
    public DoOpResponse createDoOpResponse() {
        return new DoOpResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoOpResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://igorkromin.net/wsat/participant", name = "doOpResponse")
    public JAXBElement<DoOpResponse> createDoOpResponse(DoOpResponse value) {
        return new JAXBElement<DoOpResponse>(_DoOpResponse_QNAME, DoOpResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoOp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://igorkromin.net/wsat/participant", name = "doOp")
    public JAXBElement<DoOp> createDoOp(DoOp value) {
        return new JAXBElement<DoOp>(_DoOp_QNAME, DoOp.class, null, value);
    }

}
