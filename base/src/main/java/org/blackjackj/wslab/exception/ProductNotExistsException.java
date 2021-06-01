package org.blackjackj.wslab.exception;

public class ProductNotExistsException extends RuntimeException {

    private static final String TEMPLATE = "product #%d not exists";

    private final long id;


    public ProductNotExistsException(long id) {
        super(String.format(TEMPLATE, id));
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
