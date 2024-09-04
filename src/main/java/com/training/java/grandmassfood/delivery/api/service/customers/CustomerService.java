package com.training.java.grandmassfood.delivery.api.service.customers;

import com.training.java.grandmassfood.delivery.api.dao.customers.dto.CustomerRequest;
import com.training.java.grandmassfood.delivery.api.dao.customers.dto.CustomerResponse;

public interface CustomerService {
    void clientExists(String clientDocument);
    Long getCustomerId(String clientDocument);
    CustomerResponse getCustomerByDocument(String clientDocument);
    CustomerResponse createCustomer(CustomerRequest customerRequest);
}
