package com.training.java.grandmassfood.delivery.api.dao.customers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreateResponse {
    @JsonProperty("document")
    private String documentNumber;

    @JsonProperty("name")
    private String fullName;

    private String email;

    @JsonProperty("phone")
    private String phoneNumber;

    @JsonProperty("deliveryAddress")
    private String shippingAddress;
}
