package com.training.java.grandmassfood.delivery.api.dao.products.dto;

import com.training.java.grandmassfood.delivery.api.dao.products.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductGetResponse {
    private UUID productUuid;
    private String comboName;
    private Category category;
    private String description;
    private Double price;
    private Boolean isAvailable;

}
