package com.training.java.grandmassfood.delivery.api.service.customers;

import com.training.java.grandmassfood.delivery.api.dao.customers.dto.CustomerList;
import com.training.java.grandmassfood.delivery.api.dao.customers.dto.CustomerListResponse;
import com.training.java.grandmassfood.delivery.api.exception.sortedlist.InvalidSortingParameter;
import com.training.java.grandmassfood.delivery.api.persistence.customers.CustomerListPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomersListServicesImpl implements CustomersListServices {

    private final CustomerListPersistence customerListPersistence;

    @Override
    public CustomerListResponse getListCustomers(String orderBy, String direction) {
        validateDirection(direction);
        validateOptionsOrderBy(orderBy) ;
        List<CustomerList> customerLists = customerListPersistence.getListCustomers(orderBy,direction);
        return CustomerListResponse.builder()
                .orderBy(orderBy)
                .direction(direction)
                .customerList(customerLists)
                .build();
    }
    private void validateDirection(String direction){
        if (!"ASC".equalsIgnoreCase(direction) && !"DESC".equalsIgnoreCase(direction)) {
            throw new InvalidSortingParameter("Invalid direction: " + direction);
        }
    }
    private void validateOptionsOrderBy(String orderBy){
        if (!"DOCUMENT".equalsIgnoreCase(orderBy) && !"NAME".equalsIgnoreCase(orderBy) && !"ADDRESS".equalsIgnoreCase(orderBy)) {
            throw new InvalidSortingParameter("Invalid Option Order: " + orderBy);
        }
    }
}
