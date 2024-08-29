package com.training.java.grandmassfood.delivery.api.exception.customers;

import com.training.java.grandmassfood.delivery.api.exception.StandardError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeAdvice {

    @ExceptionHandler(CustomerNotFoundException.class)
    ResponseEntity<StandardError> handleCustomerNotFoundException(CustomerNotFoundException customerNotFoundException) {
        return ResponseEntity.status(customerNotFoundException.getHttpStatus())
                .body(customerNotFoundException.getStandardError());
    }

}
