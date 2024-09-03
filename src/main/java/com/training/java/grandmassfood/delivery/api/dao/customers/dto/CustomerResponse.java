package com.training.java.grandmassfood.delivery.api.dao.customers.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private String documentNumber;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String shippingAddress;
}
