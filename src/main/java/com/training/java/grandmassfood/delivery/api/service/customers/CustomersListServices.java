package com.training.java.grandmassfood.delivery.api.service.customers;

import com.training.java.grandmassfood.delivery.api.dao.customers.dto.CustomerListResponse;

public interface CustomersListServices {
    CustomerListResponse getListCustomers(String orderBy, String direction);
}
