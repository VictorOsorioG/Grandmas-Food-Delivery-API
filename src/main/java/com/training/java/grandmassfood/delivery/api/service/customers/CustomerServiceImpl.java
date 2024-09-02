package com.training.java.grandmassfood.delivery.api.service.customers;

import com.training.java.grandmassfood.delivery.api.dao.customers.dto.CustomerResponse;
import com.training.java.grandmassfood.delivery.api.dao.customers.entity.Customer;
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
    @Override
    public CustomerResponse getCustomerByDocument(String clientDocument) {
        //to do : validar que sea un formato de documento válido. Si no lo es mandar un mensaje de formato de documento inválido con el status 400
        //Formato válido: CC-
        //                CE-
        //                P-# (máximo 20 caracteres)
        // Si no cumple, retorna excepción (crear una excepción ClientDocumentNotValidException
        return customerPersistence.getCustomerByDocument(clientDocument);
    }

}
