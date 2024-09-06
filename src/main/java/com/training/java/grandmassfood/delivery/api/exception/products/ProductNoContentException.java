package com.training.java.grandmassfood.delivery.api.exception.products;

import com.training.java.grandmassfood.delivery.api.exception.StandardException;
import org.springframework.http.HttpStatus;

public class ProductNoContentException extends StandardException {
    public ProductNoContentException() {
        super("E1003", HttpStatus.CONFLICT, "Invalid content, no changes");
    }
}
