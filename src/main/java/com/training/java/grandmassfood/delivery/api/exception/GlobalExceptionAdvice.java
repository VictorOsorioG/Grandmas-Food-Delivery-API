package com.training.java.grandmassfood.delivery.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ResponseEntity<StandardError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException argumentTypeMismatchException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(StandardError.builder()
                        .code("E1004")
                        .timestamp(LocalDateTime.now())
                        .description("Failed Uuid value: "+argumentTypeMismatchException.getMessage())
                        .exception(argumentTypeMismatchException.getClass().getSimpleName())
                        .build());
    }
}
