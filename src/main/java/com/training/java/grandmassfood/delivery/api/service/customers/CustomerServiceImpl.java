package com.training.java.grandmassfood.delivery.api.service.customers;

import com.training.java.grandmassfood.delivery.api.dao.customers.dto.CustomerRequest;
import com.training.java.grandmassfood.delivery.api.dao.customers.dto.CustomerResponse;
import com.training.java.grandmassfood.delivery.api.exception.customers.ClientAlreadyExists;
import com.training.java.grandmassfood.delivery.api.exception.customers.ClientDocumentNotValidException;
import com.training.java.grandmassfood.delivery.api.exception.customers.CustomerNotFoundException;
import com.training.java.grandmassfood.delivery.api.exception.customers.NoChangesToUpdateException;
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

    private static final String LOG_PREFIX = "CustomerService >>>";

    private final CustomerPersistence customerPersistence;

    @Override
    public void clientExists(String clientDocument) {
        log.info("{} Checking if client {} exit", LOG_PREFIX, clientDocument);
        if (!customerPersistence.clientExists(clientDocument)) {
            throw new CustomerNotFoundException(clientDocument);
        }
    }

    @Override
    public Long getCustomerId(String clientDocument) {
        log.info("{} Getting client {} id", LOG_PREFIX, clientDocument);
        Long customerId = customerPersistence.getCustomerId(clientDocument);
        if (Objects.isNull(customerId)) {
            throw new CustomerNotFoundException(clientDocument);
        }
        return customerId;
    }

    @Override
    public CustomerResponse getCustomerByDocument(String clientDocument) {
        isValidClientDocument(clientDocument);
        log.info("{} Getting client {}", LOG_PREFIX, clientDocument);
        return customerPersistence.getCustomerByDocument(clientDocument);
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        isValidClientDocument(customerRequest.getDocumentNumber());
        isClientAlreadyExists(customerRequest.getDocumentNumber());
        log.info("{} Creating client", LOG_PREFIX);
        return customerPersistence.createCustomer(customerRequest);
    }

    @Override
    public void updateCustomer(String document, CustomerRequest customerRequest) {
        clientExists(document);
        customerHasDifferentFields(document, customerRequest);
        log.info("{} Updating client {}", LOG_PREFIX, document);
        customerPersistence.updateCustomer(document, customerRequest);
    }

    @Override
    public void deleteCustomer(String document) {
        clientExists(document);
        log.info("{} Deleting client {}", LOG_PREFIX, document);
        customerPersistence.deleteCustomer(document);
    }

    private void customerHasDifferentFields(String currentDocument, CustomerRequest updatedCustomer) {
        CustomerResponse currentCustomer = customerPersistence.getCustomerByDocument(currentDocument);
        if (customerEquals(updatedCustomer, currentCustomer)) {
            throw new NoChangesToUpdateException(currentDocument);
        }
    }

    private boolean customerEquals(CustomerRequest updatedCustomer, CustomerResponse currentCustomer) {
        return Objects.equals(updatedCustomer.getDocumentNumber(), currentCustomer.getDocumentNumber())
                && Objects.equals(updatedCustomer.getFullName(), currentCustomer.getFullName())
                && Objects.equals(updatedCustomer.getEmail(), currentCustomer.getEmail())
                && Objects.equals(updatedCustomer.getPhoneNumber(), currentCustomer.getPhoneNumber())
                && Objects.equals(updatedCustomer.getShippingAddress(), currentCustomer.getShippingAddress());
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
