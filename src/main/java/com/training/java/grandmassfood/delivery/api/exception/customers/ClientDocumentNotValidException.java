package com.training.java.grandmassfood.delivery.api.exception.customers;

import com.training.java.grandmassfood.delivery.api.exception.StandardException;
import org.springframework.http.HttpStatus;

public class ClientDocumentNotValidException extends StandardException {

    public ClientDocumentNotValidException(String clientDocument) {
        super("E1003", HttpStatus.BAD_REQUEST, "The client document format is not valid: " + clientDocument);
    }
}