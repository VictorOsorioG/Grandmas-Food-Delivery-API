package com.training.java.grandmassfood.delivery.api.exception.customers;

import com.training.java.grandmassfood.delivery.api.exception.StandardError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerAdvice {

    @ExceptionHandler(CustomerNotFoundException.class)
    ResponseEntity<StandardError> handleCustomerNotFoundException(CustomerNotFoundException customerNotFoundException) {
        return ResponseEntity.status(customerNotFoundException.getHttpStatus())
                .body(customerNotFoundException.getStandardError());
    }

    @ExceptionHandler(ClientDocumentNotValidException.class)
    ResponseEntity<StandardError> handleClientDocumentNotValidException(ClientDocumentNotValidException clientDocumentNotValidException) {
        return ResponseEntity.status(clientDocumentNotValidException.getHttpStatus())
                .body(clientDocumentNotValidException.getStandardError());
    }

}
