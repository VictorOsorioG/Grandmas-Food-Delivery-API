package com.training.java.grandmassfood.delivery.api.dao.products.dto;

import com.training.java.grandmassfood.delivery.api.dao.products.entity.Category;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @Size(max = 255, message = "Combo Name Fantasy has a limit of 255 characters")
    @NotBlank(message = "Combo Name Fantasy is mandatory")
    private String comboName;

    @NotNull(message = "Category field cannot be null")
    private Category category;

    @Size(max = 511, message = "Product description has a limit of 255 characters")
    @NotBlank(message = "Combo Name Fantasy is mandatory")
    private String description;

    @DecimalMin(value = "0.01", inclusive = false, message = "The number must be greater than zero")
    @NotNull(message = "Price field cannot be null")
    private Double price;
    @NotNull(message = "isAvailable field cannot be null")
    private Boolean isAvailable;
}
