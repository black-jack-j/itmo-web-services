package org.blackjackj.wslab.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.ws.WebFault;

@Data
@NoArgsConstructor
@WebFault(faultBean = "org.blackjackj.wslab.exception.ProductNotFoundFault")
public class ProductNotFoundException extends Exception {

    private ProductNotFoundFault productNotFoundFault;

    public ProductNotFoundException(String message, ProductNotFoundFault productNotFoundFault) {
        super(message);
        this.productNotFoundFault = productNotFoundFault;
    }

    public ProductNotFoundException(String message, ProductNotFoundFault productNotFoundFault, Throwable cause) {
        super(message, cause);
        this.productNotFoundFault = productNotFoundFault;
    }

    public ProductNotFoundFault getFaultInfo() {
        return this.productNotFoundFault;
    }

}
