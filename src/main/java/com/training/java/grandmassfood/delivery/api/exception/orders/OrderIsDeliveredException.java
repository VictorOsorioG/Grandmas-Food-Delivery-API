package com.training.java.grandmassfood.delivery.api.exception.orders;

import com.training.java.grandmassfood.delivery.api.exception.StandardException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class OrderIsDeliveredException extends StandardException {
    public OrderIsDeliveredException(UUID orderUuid) {
        super("E1007", HttpStatus.CONFLICT, "Order " + orderUuid + " is already delivered");
    }
}
