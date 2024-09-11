package com.training.java.grandmassfood.delivery.api.dao.products.dto.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.training.java.grandmassfood.delivery.api.dao.products.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductList {
    private Category category;
    private String fantasyName;
    private double priceWithTax;
}
