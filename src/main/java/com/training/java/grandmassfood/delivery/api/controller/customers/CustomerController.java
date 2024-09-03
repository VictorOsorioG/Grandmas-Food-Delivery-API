package com.training.java.grandmassfood.delivery.api.controller.customers;

import com.training.java.grandmassfood.delivery.api.dao.customers.dto.CustomerResponse;
import com.training.java.grandmassfood.delivery.api.dao.customers.entity.Customer;
import com.training.java.grandmassfood.delivery.api.service.customers.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{document}")
    //@ResponseStatus(HttpStatus.OK)
    public CustomerResponse getCustomer(@PathVariable String document) {
        return customerService.getCustomerByDocument(document);
    }
}
