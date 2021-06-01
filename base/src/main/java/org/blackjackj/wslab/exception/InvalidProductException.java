package org.blackjackj.wslab.exception;

public class InvalidProductException extends RuntimeException {

    private static final String TEMPLATE = "non-valid argument provided. reason: %s";

    public InvalidProductException(String reason) {
        super(String.format(TEMPLATE, reason));
    }
}
