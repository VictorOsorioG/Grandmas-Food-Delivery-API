package com.training.java.grandmassfood.delivery.api.exception.products;

import com.training.java.grandmassfood.delivery.api.exception.StandardException;
import org.springframework.http.HttpStatus;

public class ProductNotSearchQuery extends StandardException {

    public ProductNotSearchQuery() {
        super("E1016", HttpStatus.BAD_REQUEST, "Search parameter cannot be empty");
    }
}
