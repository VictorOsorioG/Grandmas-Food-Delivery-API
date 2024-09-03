package com.training.java.grandmassfood.delivery.api.exception.customers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClientDocumentNotValidException extends RuntimeException {

    public ClientDocumentNotValidException(String message) {
        super(message);
    }
}