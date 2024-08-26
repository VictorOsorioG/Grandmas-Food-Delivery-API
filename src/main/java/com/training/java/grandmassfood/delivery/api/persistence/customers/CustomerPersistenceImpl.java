package com.training.java.grandmassfood.delivery.api.persistence.customers;

import com.training.java.grandmassfood.delivery.api.dao.customers.entity.Customer;
import com.training.java.grandmassfood.delivery.api.dao.customers.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerPersistenceImpl implements CustomerPersistence {

    private final CustomerRepository customerRepository;

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
}
