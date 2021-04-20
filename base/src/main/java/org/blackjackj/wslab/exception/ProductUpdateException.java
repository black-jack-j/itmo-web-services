package org.blackjackj.wslab.exception;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "org.blackjackj.wslab.exception.ProductUpdateFaule")
public class ProductUpdateException extends Exception {

    private ProductUpdateFault productUpdateFault;

    public ProductUpdateException(String message, ProductUpdateFault productUpdateFault) {
        super(message);
        this.productUpdateFault = productUpdateFault;
    }

    public ProductUpdateException(String message, ProductUpdateFault productUpdateFault, Throwable cause) {
        super(message, cause);
        this.productUpdateFault = productUpdateFault;
    }

    public ProductUpdateFault getFaultInfo() {
        return this.productUpdateFault;
    }

}
