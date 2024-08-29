package com.training.java.grandmassfood.delivery.api.exception.products;

import com.training.java.grandmassfood.delivery.api.exception.StandardError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    ResponseEntity<StandardError> handleProductNotFoundException(ProductNotFoundException productNotFoundException) {
        return ResponseEntity.status(productNotFoundException.getHttpStatus())
                .body(productNotFoundException.getStandardError());
    }

    @ExceptionHandler(ProductNotAvailableException.class)
    ResponseEntity<StandardError> handleProductNotAvailableException(ProductNotAvailableException productNotAvailableException) {
        return ResponseEntity.status(productNotAvailableException.getHttpStatus())
                .body(productNotAvailableException.getStandardError());
    }

}
