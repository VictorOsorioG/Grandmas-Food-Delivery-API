package com.training.java.grandmassfood.delivery.api.exception;

import com.training.java.grandmassfood.delivery.api.dao.products.entity.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ResponseEntity<StandardError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException argumentTypeMismatchException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(StandardError.builder()
                        .code("E1004")
                        .timestamp(LocalDateTime.now())
                        .description("Failed Uuid value: " + argumentTypeMismatchException.getMessage())
                        .exception(argumentTypeMismatchException.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<StandardError> MethodArgumentNotValidException(MethodArgumentNotValidException argumentNotValidException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(StandardError.builder()
                        .code("E1011")
                        .timestamp(LocalDateTime.now())
                        .description(argumentNotValidException.getFieldErrors().stream()
                                .map(FieldError::getDefaultMessage)
                                .collect(Collectors.joining()))
                        .exception(argumentNotValidException.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<StandardError> HttpMessageNotReadableException(HttpMessageNotReadableException messageNotReadableException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(StandardError.builder()
                        .code("E1012")
                        .timestamp(LocalDateTime.now())
                        .description("Type a valid value: " + showValuesEnum())
                        .exception(messageNotReadableException.getClass().getSimpleName())
                        .build());
    }
    public String showValuesEnum() {
        StringBuilder sb = new StringBuilder();
        for (Category category : Category.values()) {
            sb.append(category.toString()).append(", ");
        }
        return sb.toString();
    }
    @ExceptionHandler(StandardException.class)
    ResponseEntity<StandardError> handleProductNotFoundException(StandardException standardException) {
        return ResponseEntity.status(standardException.getHttpStatus())
                .body(standardException.getStandardError());
    }
}
