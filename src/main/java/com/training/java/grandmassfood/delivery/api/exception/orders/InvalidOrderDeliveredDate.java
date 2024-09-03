package com.training.java.grandmassfood.delivery.api.exception.orders;

import com.training.java.grandmassfood.delivery.api.exception.StandardException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class InvalidOrderDeliveredDate extends StandardException {
    public InvalidOrderDeliveredDate(LocalDateTime timestamp) {
        super("E1005", HttpStatus.BAD_REQUEST, "Delivered date " + timestamp + " is in the future");
    }
}
