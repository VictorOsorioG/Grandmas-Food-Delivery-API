package com.training.java.grandmassfood.delivery.api.controller.customers;

import com.training.java.grandmassfood.delivery.api.dao.customers.dto.CustomerRequest;
import com.training.java.grandmassfood.delivery.api.dao.customers.dto.CustomerResponse;
import com.training.java.grandmassfood.delivery.api.service.customers.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{document}")
    @Operation(summary = "Get the products by document")
    public CustomerResponse getCustomer(@PathVariable String document) {
        return customerService.getCustomerByDocument(document);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Post in DB and create customer from body")
    public CustomerResponse createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        return customerService.createCustomer(customerRequest);
    }

    @PutMapping("/{document}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Update customer in DB based on document")
    public void updateCustomer(@PathVariable String document, @Valid @RequestBody CustomerRequest customerRequest) {
        customerService.updateCustomer(document, customerRequest);
    }

    @DeleteMapping("/{document}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete customer from DB based on document")
    public void deleteCustomer(@PathVariable String document) {
        customerService.deleteCustomer(document);
    }
}
