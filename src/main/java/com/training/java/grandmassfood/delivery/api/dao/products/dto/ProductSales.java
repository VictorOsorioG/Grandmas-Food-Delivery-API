package com.training.java.grandmassfood.delivery.api.dao.products.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSales {
    private String comboName;
    private int quantity;
    private double grossSales;
}
