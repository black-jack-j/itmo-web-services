package org.blackjackj.wslab.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundFault {

    private static final String DEFAULT_MESSAGE = "product id cannot be null";
    private static final String MESSAGE_FORMAT = "no product with id: %d";

    private String message;

    public static ProductNotFoundFault nullValue() {
        ProductNotFoundFault productNotFoundFault = new ProductNotFoundFault();
        productNotFoundFault.setMessage(DEFAULT_MESSAGE);
        return productNotFoundFault;
    }

    public static ProductNotFoundFault withId(long id) {
        ProductNotFoundFault productNotFoundFault = new ProductNotFoundFault();
        productNotFoundFault.setMessage(String.format(MESSAGE_FORMAT, id));
        return productNotFoundFault;
    }

}
