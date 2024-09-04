package com.training.java.grandmassfood.delivery.api.exception.customers;

import com.training.java.grandmassfood.delivery.api.exception.StandardException;
import org.springframework.http.HttpStatus;

public class NoChangesToUpdateException extends StandardException {
    public NoChangesToUpdateException(String clientDocument) {
        super("E1009", HttpStatus.CONFLICT, "No changes found in the customer " + clientDocument + " fields.");
    }
}
