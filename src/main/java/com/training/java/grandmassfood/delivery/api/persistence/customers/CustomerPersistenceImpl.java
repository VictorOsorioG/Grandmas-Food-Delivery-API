package com.training.java.grandmassfood.delivery.api.persistence.customers;

import com.training.java.grandmassfood.delivery.api.dao.customers.dto.CustomerRequest;
import com.training.java.grandmassfood.delivery.api.dao.customers.dto.CustomerResponse;
import com.training.java.grandmassfood.delivery.api.dao.customers.entity.Customer;
import com.training.java.grandmassfood.delivery.api.dao.customers.repository.CustomerRepository;
import com.training.java.grandmassfood.delivery.api.exception.customers.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerPersistenceImpl implements CustomerPersistence {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;


    @Override
    @Transactional(readOnly = true)
    public boolean clientExists(String clientDocument) {
        return customerRepository.existsByDocumentNumber(clientDocument);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCustomerId(String clientDocument) {
        return customerRepository.getIdByDocumentNumber(clientDocument);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer getCustomerReference(Long customerId) {
        return customerRepository.getReferenceById(customerId);
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerResponse getCustomerByDocument(String clientDocument) {
        return customerRepository.findByDocumentNumber(clientDocument)
                .map(customer->modelMapper.map(customer,CustomerResponse.class))
                .orElseThrow(()->new CustomerNotFoundException(clientDocument));
    }

    @Transactional(readOnly = false)
    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = modelMapper.map(customerRequest, Customer.class);
        customer.setCreatedAt(LocalDateTime.now());
        customerRepository.save(customer);
        return modelMapper.map(customer, CustomerResponse.class);
    }

    @Override
    @Transactional
    public void updateCustomer(String currentDocument, CustomerRequest customerRequest) {
        customerRepository.update(currentDocument,
                customerRequest.getDocumentNumber(),
                customerRequest.getFullName(),
                customerRequest.getEmail(),
                customerRequest.getPhoneNumber(),
                customerRequest.getShippingAddress()
        );
    }
}
