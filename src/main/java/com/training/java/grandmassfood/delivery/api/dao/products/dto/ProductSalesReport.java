package com.training.java.grandmassfood.delivery.api.dao.products.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSalesReport {
    List<String> bestSales;
    List<String> worseSales;
    List<ProductSales> productSales;
}
