package com.training.java.grandmassfood.delivery.api.exception.salesreport;

import com.training.java.grandmassfood.delivery.api.exception.StandardException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class InvalidDateRange extends StandardException {
    public InvalidDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        super("E1015", HttpStatus.BAD_REQUEST, "The start date " + startDate + " is after the end date " + endDate);
    }
}
