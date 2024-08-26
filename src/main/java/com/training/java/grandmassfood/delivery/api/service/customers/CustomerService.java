package com.training.java.grandmassfood.delivery.api.service.customers;

public interface CustomerService {
    void clientExists(String clientDocument);
    Long getCustomerId(String clientDocument);
}