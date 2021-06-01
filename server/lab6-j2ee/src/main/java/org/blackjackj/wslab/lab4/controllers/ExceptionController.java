package org.blackjackj.wslab.lab4.controllers;

import org.blackjackj.wslab.exception.InvalidProductException;
import org.blackjackj.wslab.exception.ProductNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ProductNotExistsException.class})
    public ResponseEntity<String> handleProductNotExists(ProductNotExistsException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({InvalidProductException.class})
    public ResponseEntity<String> handleInvalidRequest(InvalidProductException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

}
