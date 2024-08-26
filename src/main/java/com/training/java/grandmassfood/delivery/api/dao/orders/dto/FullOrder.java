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
public class FullOrder {

    private UUID uuid;
    private Long customerId;
    private Long productId;
    private LocalDateTime orderDate;
    private int quantity;
    private String additionalInfo;
    private Double subtotal;
    private Double iva;
    private Double total;
}
