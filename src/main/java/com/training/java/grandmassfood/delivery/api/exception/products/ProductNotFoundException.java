package com.training.java.grandmassfood.delivery.api.exception.products;

import com.training.java.grandmassfood.delivery.api.exception.StandardException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class ProductNotFoundException extends StandardException {

    public ProductNotFoundException(UUID uuid) {
        super("E1001", HttpStatus.NOT_FOUND, "Could not find the product " + uuid);
    }
}
