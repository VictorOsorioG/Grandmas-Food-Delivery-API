package com.training.java.grandmassfood.delivery.api.persistence.customers;

import com.training.java.grandmassfood.delivery.api.dao.customers.dto.CustomerList;
import com.training.java.grandmassfood.delivery.api.dao.customers.entity.Customer;
import com.training.java.grandmassfood.delivery.api.dao.customers.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerListPersistenceImpl implements CustomerListPersistence {

    private static final String LOG_PREFIX = "CustomerListPersistence >>>";

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CustomerList> getListCustomers(String orderBy, String direction) {
       if ( "DESC".equalsIgnoreCase(direction)){
           return customerRepository.getListCustomerByDesc(orderBy).stream()
                   .map(view -> modelMapper.map(view, CustomerList.class))
                   .toList();
       }else{
           return customerRepository.getListCustomerByAsc(orderBy).stream()
                   .map(view -> modelMapper.map(view, CustomerList.class))
                   .toList();
       }
    }
}
