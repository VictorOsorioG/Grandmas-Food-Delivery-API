package com.training.java.grandmassfood.delivery.api.dao.customers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerList {
    private String documentNumber;
    private String fullName;
    private String shippingAddress;
}
