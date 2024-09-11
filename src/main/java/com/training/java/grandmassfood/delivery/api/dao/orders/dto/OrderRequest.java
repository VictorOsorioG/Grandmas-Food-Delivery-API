package com.training.java.grandmassfood.delivery.api.dao.orders.dto;

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
public class OrderRequest {

    @Size(max = 20, message = "Document has a limit of 20 characters")
    @NotBlank(message = "Client document is mandatory")
    private String clientDocument;

    @NotNull(message = "Product uuid is mandatory")
    private UUID productUuid;

    @Min(value = 1, message = "Quantity should not be less than 1")
    @Max(value = 100, message = "Quantity should not be greater than 100")
    private int quantity;

    @Size(max = 511, message = "Extra information has a limit of 511 characters")
    private String extraInformation;
}
