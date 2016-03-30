package net.igorkromin;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.ws.BindingType;
import javax.xml.ws.Holder;

@WebService(
    name="WsExamplePortType",
    portName="WsExamplePort",
    serviceName="WsExampleService",
    targetNamespace="http://igorkromin.net"
    )
@SOAPBinding(
    style=SOAPBinding.Style.DOCUMENT,
    use=SOAPBinding.Use.LITERAL,
    parameterStyle=SOAPBinding.ParameterStyle.WRAPPED
    )
@BindingType(value = javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class WsExampleImpl {

    public WsExampleImpl() {
        super();
    }

    @WebMethod()
    public void getData(
        @XmlElement(required=true)
        @WebParam(mode = WebParam.Mode.IN, name = "dataId")
        String dataId,
        @WebParam(mode = WebParam.Mode.OUT, name = "returnData")
        Holder<String> returnData
    ) {
         returnData.value = (new ExampleData(dataId)).toString();
    }
}
