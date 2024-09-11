package com.training.java.grandmassfood.delivery.api.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StandardError {
    private String code;
    private LocalDateTime timestamp;
    private String description;
    private String exception;
}
