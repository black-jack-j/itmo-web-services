package org.blackjackj.wslab.exception;

public class UmbrellaException extends RuntimeException {

    private static final String message = "errors during request: %s";

    public UmbrellaException(String message) {
        super(String.format(message, message));
    }
}
