package com.training.java.grandmassfood.delivery.api.exception.customers;

import com.training.java.grandmassfood.delivery.api.exception.StandardException;
import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends StandardException {

    public CustomerNotFoundException(String clientDocument) {
        super("E1000", HttpStatus.NOT_FOUND, "Customer with document " + clientDocument + " was not found");
    }
}
