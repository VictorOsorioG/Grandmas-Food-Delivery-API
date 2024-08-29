package com.training.java.grandmassfood.delivery.api.service.customers;

import com.training.java.grandmassfood.delivery.api.exception.customers.CustomerNotFoundException;
import com.training.java.grandmassfood.delivery.api.persistence.customers.CustomerPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerPersistence customerPersistence;

    @Override
    public void clientExists(String clientDocument) {
        if (!customerPersistence.clientExists(clientDocument)) {
            throw new CustomerNotFoundException(clientDocument);
        }
    }

    @Override
    public Long getCustomerId(String clientDocument) {
        Long customerId = customerPersistence.getCustomerId(clientDocument);
        if (Objects.isNull(customerId)) {
            throw new CustomerNotFoundException(clientDocument);
        }
        return customerId;
    }
}
