package org.blackjackj.wslab.exception;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "org.blackjackj.wslab.exception.UnauthorizedFault")
public class UnauthorizedException extends Exception{

    private UnauthorizedFault unauthorizedFault;

    public UnauthorizedException(String message, UnauthorizedFault unauthorizedFault) {
        super(message);
        this.unauthorizedFault = unauthorizedFault;
    }

    public UnauthorizedException(String message, UnauthorizedFault unauthorizedFault, Throwable cause) {
        super(message, cause);
        this.unauthorizedFault = unauthorizedFault;
    }

    public UnauthorizedFault getFaultInfo() {
        return this.unauthorizedFault;
    }

}
