package com.training.java.grandmassfood.delivery.api.persistence.customers;

import com.training.java.grandmassfood.delivery.api.dao.customers.dto.CustomerRequest;
import com.training.java.grandmassfood.delivery.api.dao.customers.dto.CustomerResponse;
import com.training.java.grandmassfood.delivery.api.dao.customers.entity.Customer;

public interface CustomerPersistence {
    boolean clientExists(String clientDocument);
    Long getCustomerId(String clientDocument);
    Customer getCustomerReference(Long customerId);
    CustomerResponse getCustomerByDocument(String clientDocument);
    CustomerResponse createCustomer(CustomerRequest customerRequest);

    void updateCustomer(String currentDocument, CustomerRequest customerRequest);
}
