package controllers.soap;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import service.AttendanceService;


import jakarta.xml.bind.DatatypeConverter;
import javax.xml.namespace.QName;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class ControllerAuthorizationHandler implements SOAPHandler<SOAPMessageContext> {

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (!outboundProperty) {
            try {
                SOAPMessage message = context.getMessage();
                SOAPHeader header = message.getSOAPHeader();
                SOAPBody body = message.getSOAPBody();

                Iterator it = header.getChildElements(new QName("http://soap.controllers/", "Authorization"));
                SOAPElement authorizationElement = it.hasNext() ? (SOAPElement) it.next() : null;
                it = body.getChildElements(new QName("http://soap.controllers/", "registerAttendance"));
                SOAPElement registerAttendanceElement = it.hasNext() ? (SOAPElement) it.next() : null;
                if(registerAttendanceElement == null)
                    it = body.getChildElements(new QName("http://soap.controllers/", "getAttendance"));
                registerAttendanceElement = it.hasNext() ? (SOAPElement) it.next() : null;

                SOAPElement userIdElement = null;
                if (registerAttendanceElement != null) {
                    it = registerAttendanceElement.getChildElements(new QName("id"));
                     userIdElement = it.hasNext() ? (SOAPElement) it.next() : null;
                }
                if (authorizationElement != null && userIdElement != null) {
                    String encodedCredentials = authorizationElement.getTextContent();
                    String decodedCredentials = new String(DatatypeConverter.parseBase64Binary(encodedCredentials), StandardCharsets.UTF_8);
                    String[] credentials = decodedCredentials.split(":", 2);
                    String username = credentials[0];
                    String password = credentials[1];

                    Long userId = Long.parseLong(userIdElement.getTextContent());

                    if (AttendanceService.getInstance().checkAuthentication(username, password, userId)) {
                        System.out.println("Authorization is successful");
                        return true;
                    } else {
                        throw new RuntimeException("Authorization failed");
                    }
                } else {
                    throw new RuntimeException("Authorization header or UserId is not found");
                }

            } catch (SOAPException e) {
                System.err.println("Error occurred while processing the SOAP message");
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
    }
}