package com.training.java.grandmassfood.delivery.api.dao.products.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductListAvailableResponse {
    List<ProductList> productLists;
}
