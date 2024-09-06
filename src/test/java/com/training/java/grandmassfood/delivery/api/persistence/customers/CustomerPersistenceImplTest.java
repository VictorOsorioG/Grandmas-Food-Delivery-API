package com.training.java.grandmassfood.delivery.api.persistence.customers;

import com.training.java.grandmassfood.delivery.api.dao.customers.dto.CustomerRequest;
import com.training.java.grandmassfood.delivery.api.dao.customers.dto.CustomerResponse;
import com.training.java.grandmassfood.delivery.api.dao.customers.entity.Customer;
import com.training.java.grandmassfood.delivery.api.dao.customers.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerPersistenceImplTest {

    @InjectMocks
    private CustomerPersistenceImpl customerPersistence;

    @Mock
    private CustomerRepository customerRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    @DisplayName("Test save customer")
    void givenClientDocument_WhenCreateCustomer_ThenReturnCustomerResponse() {
        String clientDocument = "CC-1234567890";
        String clientName = "client";
        String clientEmail = "sherlock@email.com";
        String clientPhone = "1234567890";
        String clientShippingAddress = "221B Baker Street";

        CustomerRequest customerRequest = CustomerRequest.builder()
                .documentNumber(clientDocument)
                .fullName(clientName)
                .email(clientEmail)
                .phoneNumber(clientPhone)
                .shippingAddress(clientShippingAddress)
                .build();

        CustomerResponse customerResponse = customerPersistence.createCustomer(customerRequest);

        verify(customerRepository)
                .save(any(Customer.class));

        assertEquals(clientDocument, customerResponse.getDocumentNumber());
        assertEquals(clientName, customerResponse.getFullName());
        assertEquals(clientEmail, customerResponse.getEmail());
        assertEquals(clientPhone, customerResponse.getPhoneNumber());
        assertEquals(clientShippingAddress, customerResponse.getShippingAddress());
    }

}