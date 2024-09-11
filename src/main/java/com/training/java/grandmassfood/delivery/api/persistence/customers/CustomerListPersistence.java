package com.training.java.grandmassfood.delivery.api.persistence.customers;

import com.training.java.grandmassfood.delivery.api.dao.customers.dto.CustomerList;

import java.util.List;

public interface CustomerListPersistence {
    List<CustomerList> getListCustomers(String orderBy, String direction);
}
