package com.training.java.grandmassfood.delivery.api.exception.orders;

import com.training.java.grandmassfood.delivery.api.exception.StandardException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class OrderNotFoundException extends StandardException {
    public OrderNotFoundException(UUID orderUuid) {
        super("E1006", HttpStatus.BAD_REQUEST, "Order " + orderUuid + " was not found");
    }
}
