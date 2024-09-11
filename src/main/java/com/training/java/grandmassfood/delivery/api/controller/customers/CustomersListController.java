package com.training.java.grandmassfood.delivery.api.controller.customers;

import com.training.java.grandmassfood.delivery.api.dao.customers.dto.CustomerListResponse;
import com.training.java.grandmassfood.delivery.api.exception.apiresponse.BadRequestApiResponse;
import com.training.java.grandmassfood.delivery.api.service.customers.CustomersListServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
@Tag(name = "Customer List Controller")
public class CustomersListController {
    private final CustomersListServices customersListServices;

    @GetMapping
    @Operation(summary = "Get sorted customer list")
    @BadRequestApiResponse
    @ResponseStatus(HttpStatus.OK)
    public CustomerListResponse getListCustomer(
            @RequestParam(value = "orderBy", defaultValue = "NAME") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return customersListServices.getListCustomers(orderBy, direction);
    }
}




