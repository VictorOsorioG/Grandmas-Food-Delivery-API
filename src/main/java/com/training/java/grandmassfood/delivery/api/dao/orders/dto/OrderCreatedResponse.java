package com.training.java.grandmassfood.delivery.api.dao.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedResponse {
    private UUID orderUuid;
    private LocalDateTime creationDateTime;
    private String clientDocument;
    private UUID productUuid;
    private Integer quantity;
    private String extraInformation;
    private Double subTotal;
    private Double tax;
    private Double grandTotal;
    private Boolean delivered;
    private LocalDateTime deliveredDate;
}
