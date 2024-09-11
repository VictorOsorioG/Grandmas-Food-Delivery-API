package com.training.java.grandmassfood.delivery.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class StandardException extends RuntimeException{

    private final HttpStatus httpStatus;
    private final StandardError standardError;

    public StandardException(String code, HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.standardError = StandardError.builder()
                .code(code)
                .timestamp(LocalDateTime.now())
                .description(message)
                .exception(this.getClass().getSimpleName())
                .build();
    }
}
