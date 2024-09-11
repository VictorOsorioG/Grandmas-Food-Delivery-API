package com.training.java.grandmassfood.delivery.api.exception.products;

import com.training.java.grandmassfood.delivery.api.exception.StandardException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class ProductNotAvailableException extends StandardException {

    public ProductNotAvailableException(UUID uuid) {
        super("E1002", HttpStatus.BAD_REQUEST, "Product is not available " + uuid);
    }
}
