package com.training.java.grandmassfood.delivery.api.exception.customers;

import com.training.java.grandmassfood.delivery.api.exception.StandardException;
import org.springframework.http.HttpStatus;

public class ClientAlreadyExists extends StandardException {
    public ClientAlreadyExists(String clientDocument) {
        super("E1008", HttpStatus.CONFLICT, "The client already exists: " + clientDocument);
    }
}
