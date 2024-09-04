package com.training.java.grandmassfood.delivery.api.dao.customers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
    @Size(max = 20, message = "Document has a limit of 20 characters")
    @NotBlank(message = "Document number is mandatory")
    private String documentNumber;

    @Size(max = 255, message = "Full name has a limit of 255 characters")
    @NotBlank(message = "Full name is mandatory")
    private String fullName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

    @Size(max = 255, message = "Shipping address has a limit of 255 characters")
    @NotBlank(message = "Shipping address is mandatory")
    private String shippingAddress;
}
