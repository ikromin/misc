package net.igorkromin;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.BindingType;
import weblogic.wsee.wstx.wsat.Transactional;

@WebService(
    name="WsAtExamplePortType",
    portName="WsAtExamplePort",
    serviceName="WsAtExampleService",
    targetNamespace="http://igorkromin.net/wsat/participant"
    )
@SOAPBinding(
    style=SOAPBinding.Style.DOCUMENT,
    use=SOAPBinding.Use.LITERAL,
    parameterStyle=SOAPBinding.ParameterStyle.WRAPPED
    )
@BindingType(value = javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class WsAtExampleImpl {

    public WsAtExampleImpl() {
        super();
    }

    @WebMethod()
    @Transactional(
    	version=Transactional.Version.WSAT12,
    	value=Transactional.TransactionFlowType.MANDATORY
    )
    public void doOp()
    {
    	/*
    	 Your Code Here - All transactional resources with which the code
    	 interacts are enlisted with the imported transaction.
    	*/
    }
}
