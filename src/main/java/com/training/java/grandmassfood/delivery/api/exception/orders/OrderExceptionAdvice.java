package com.training.java.grandmassfood.delivery.api.exception.orders;

import com.training.java.grandmassfood.delivery.api.exception.StandardError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderExceptionAdvice {

    @ExceptionHandler(OrderNotFoundException.class)
    ResponseEntity<StandardError> handleOrderNotFoundException(OrderNotFoundException orderNotFoundException) {
        return ResponseEntity.status(orderNotFoundException.getHttpStatus())
                .body(orderNotFoundException.getStandardError());
    }

    @ExceptionHandler(OrderIsDeliveredException.class)
    ResponseEntity<StandardError> handleOrderIsDeliveredException(OrderIsDeliveredException orderIsDeliveredException) {
        return ResponseEntity.status(orderIsDeliveredException.getHttpStatus())
                .body(orderIsDeliveredException.getStandardError());
    }

    @ExceptionHandler(InvalidOrderDeliveredDate.class)
    ResponseEntity<StandardError> handleInvalidOrderDeliveredDate(InvalidOrderDeliveredDate invalidOrderDeliveredDate) {
        return ResponseEntity.status(invalidOrderDeliveredDate.getHttpStatus())
                .body(invalidOrderDeliveredDate.getStandardError());
    }
}
