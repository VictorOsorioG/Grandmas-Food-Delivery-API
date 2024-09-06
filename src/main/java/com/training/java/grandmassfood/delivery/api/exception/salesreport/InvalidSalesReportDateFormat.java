package com.training.java.grandmassfood.delivery.api.exception.salesreport;

import com.training.java.grandmassfood.delivery.api.exception.StandardException;
import org.springframework.http.HttpStatus;

public class InvalidSalesReportDateFormat extends StandardException {
    public InvalidSalesReportDateFormat(String date) {
        super("E1014", HttpStatus.BAD_REQUEST, "Invalid date format " + date + ". Valid format yyyyMMdd");
    }
}
