package org.blackjackj.wslab.exception;

import org.blackjackj.wslab.entity.Product;

import javax.xml.ws.WebFault;
import java.sql.SQLException;

@WebFault(faultBean = "org.blackjackj.wslab.exception.ProductCreateFault")
public class ProductCreateException extends Exception {

    private ProductCreateFault productCreateFault;

    public ProductCreateException(String message, ProductCreateFault fault) {
        super(message);
        this.productCreateFault = fault;
    }

    public ProductCreateException(String message, ProductCreateFault fault, Throwable cause) {
        super(message, cause);
        this.productCreateFault = fault;
    }

    public ProductCreateFault getFaultInfo() {
        return productCreateFault;
    }
}
