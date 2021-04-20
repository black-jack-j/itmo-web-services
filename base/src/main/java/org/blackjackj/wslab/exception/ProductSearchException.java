package org.blackjackj.wslab.exception;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.ws.WebFault;

@Data
@NoArgsConstructor
@WebFault(faultBean = "org.blackjackj.wslab.exception.ProductSearchFault")
public class ProductSearchException extends Exception{

    private ProductSearchFault productSearchFault;

    public ProductSearchException(String message, ProductSearchFault productSearchFault) {
        super(message);
        this.productSearchFault = productSearchFault;
    }

    public ProductSearchException(String message, ProductSearchFault productSearchFault, Throwable cause) {
        super(message, cause);
        this.productSearchFault = productSearchFault;
    }

    public ProductSearchFault getFaultInfo() {
        return this.productSearchFault;
    }
    
}
