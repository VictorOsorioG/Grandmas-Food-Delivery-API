package com.training.java.grandmassfood.delivery.api.dao.orderitems.dto;

import com.training.java.grandmassfood.delivery.api.dao.products.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemsDto {
    private Product product;
    private Integer quantity;
    private String additionalInfo;
    private Double price;
}
