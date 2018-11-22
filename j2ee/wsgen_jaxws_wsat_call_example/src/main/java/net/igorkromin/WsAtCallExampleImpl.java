package net.igorkromin;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.transaction.UserTransaction;
import javax.xml.ws.BindingType;
import net.igorkromin.wsat.participant.*;
import weblogic.wsee.wstx.wsat.Transactional.TransactionFlowType;
import weblogic.wsee.wstx.wsat.Transactional.Version;
import weblogic.wsee.wstx.wsat.TransactionalFeature;

@WebService(
    name="WsAtCallExamplePortType",
    portName="WsAtCallExamplePort",
    serviceName="WsAtCallExampleService",
    targetNamespace="http://igorkromin.net/wsat/coordinator"
    )
@SOAPBinding(
    style=SOAPBinding.Style.DOCUMENT,
    use=SOAPBinding.Use.LITERAL,
    parameterStyle=SOAPBinding.ParameterStyle.WRAPPED
    )
@BindingType(value = javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class WsAtCallExampleImpl {

    public WsAtCallExampleImpl() {
        super();
    }

    @WebMethod()
    public void doAtomicOp()
        throws Exception
    {
        Context ctx = null;
        UserTransaction txn = null;
        
        try {
            ctx = new InitialContext();
            txn = (UserTransaction) ctx.lookup("javax.transaction.UserTransaction");

            txn.begin();

            TransactionalFeature wsatFeature = new TransactionalFeature();
            wsatFeature.setFlowType(TransactionFlowType.MANDATORY);
            wsatFeature.setVersion(Version.WSAT12);

            WsAtExampleService wsatService = new WsAtExampleService();
            WsAtExamplePortType wsatPort = wsatService.getWsAtExamplePort(wsatFeature);

            wsatPort.doOp();

            if (txn.getStatus() == javax.transaction.Status.STATUS_MARKED_ROLLBACK) {
                throw new RuntimeException("Transaction marked for rollback");
            }
            txn.commit();
        }
        catch (Exception e) {
            txn.rollback();
            throw e;
        }
    }
}
