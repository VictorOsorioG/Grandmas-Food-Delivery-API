package com.training.java.grandmassfood.delivery.api.exception.sortedlist;

import com.training.java.grandmassfood.delivery.api.exception.StandardException;
import org.springframework.http.HttpStatus;

public class InvalidSortingParameter extends StandardException {
    public InvalidSortingParameter(String parameter) {
        super("E1017", HttpStatus.BAD_REQUEST, parameter);
    }
}
