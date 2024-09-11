package com.training.java.grandmassfood.delivery.api.exception.products;

import com.training.java.grandmassfood.delivery.api.exception.StandardException;
import org.springframework.http.HttpStatus;

public class ProductNotAvailableComboName extends StandardException {
    public ProductNotAvailableComboName(String comboName) {

        super("E1010", HttpStatus.CONFLICT,"The product combo name already exists: "+ comboName);

    }
}
