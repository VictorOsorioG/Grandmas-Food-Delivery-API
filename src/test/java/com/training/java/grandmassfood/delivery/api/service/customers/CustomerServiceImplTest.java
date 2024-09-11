package com.training.java.grandmassfood.delivery.api.service.customers;

import com.training.java.grandmassfood.delivery.api.exception.customers.CustomerNotFoundException;
import com.training.java.grandmassfood.delivery.api.persistence.customers.CustomerPersistence;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerPersistence customerPersistence;

    @Test
    @DisplayName("Test success get customer id")
    void givenClientDocument_WhenGetCustomerId_ThenReturnId() {
        String clientDocument = "CC-1234567890";
        Long clientId = 1L;
        when(customerPersistence.getCustomerId(clientDocument))
                .thenReturn(clientId);

        Long clientIdResponse = customerService.getCustomerId(clientDocument);

        assertEquals(clientId, clientIdResponse);
        verify(customerPersistence)
                .getCustomerId(clientDocument);
    }

    @Test
    @DisplayName("Test exception get cutomer id")
    void givenNonExistedClientDocument_WhenGetCustomerId_ThenThrow() {
        String nonExistedClientDocument = "CC-0123456789";
        when(customerPersistence.getCustomerId(nonExistedClientDocument))
                .thenReturn(null);

        Exception exception = assertThrows(CustomerNotFoundException.class,
                () -> customerService.getCustomerId(nonExistedClientDocument));

        assertTrue(exception.getMessage().contains(nonExistedClientDocument));
        verify(customerPersistence)
                .getCustomerId(nonExistedClientDocument);
    }
}