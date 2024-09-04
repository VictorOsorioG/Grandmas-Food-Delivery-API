package com.training.java.grandmassfood.delivery.api.service.customers;

import com.training.java.grandmassfood.delivery.api.dao.customers.dto.CustomerRequest;
import com.training.java.grandmassfood.delivery.api.dao.customers.dto.CustomerResponse;
import com.training.java.grandmassfood.delivery.api.exception.customers.ClientAlreadyExists;
import com.training.java.grandmassfood.delivery.api.exception.customers.ClientDocumentNotValidException;
import com.training.java.grandmassfood.delivery.api.exception.customers.CustomerNotFoundException;
import com.training.java.grandmassfood.delivery.api.persistence.customers.CustomerPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.regex.Pattern;

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

    @Override
    public CustomerResponse getCustomerByDocument(String clientDocument) {
        isValidClientDocument(clientDocument);
        return customerPersistence.getCustomerByDocument(clientDocument);
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        isValidClientDocument(customerRequest.getDocumentNumber());
        isClientAlreadyExists(customerRequest.getDocumentNumber());
        return customerPersistence.createCustomer(customerRequest);
    }

    private void isClientAlreadyExists(String clientDocument) {
        if (customerPersistence.clientExists(clientDocument)) {
            throw new ClientAlreadyExists(clientDocument);
        }
    }

    private void isValidClientDocument(String clientDocument) {
        String regex = "^(CC|CE|P)-\\d{1,20}$";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(clientDocument).matches()) {
            throw new ClientDocumentNotValidException(clientDocument);
        }
    }
}
