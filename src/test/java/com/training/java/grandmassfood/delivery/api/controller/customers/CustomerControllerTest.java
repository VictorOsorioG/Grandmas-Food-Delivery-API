package com.training.java.grandmassfood.delivery.api.controller.customers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.training.java.grandmassfood.delivery.api.dao.customers.dto.CustomerRequest;
import com.training.java.grandmassfood.delivery.api.dao.customers.dto.CustomerResponse;
import com.training.java.grandmassfood.delivery.api.service.customers.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    private MockMvc mockMvc;
    ObjectWriter objectWriter;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
        objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
    }

    @Test
    @DisplayName("Test MockMVC create customer")
    void givenCustomerRequest_WhenPostCustomer_ThenReturnCustomerResponse() throws Exception {
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
        String customerRequestJson = objectWriter.writeValueAsString(customerRequest);

        CustomerResponse customerResponse = CustomerResponse.builder()
                .documentNumber(clientDocument)
                .fullName(clientName)
                .email(clientEmail)
                .phoneNumber(clientPhone)
                .shippingAddress(clientShippingAddress)
                .build();

        when(customerService.createCustomer(customerRequest))
                .thenReturn(customerResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerRequestJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.documentNumber").value(clientDocument))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value(clientName))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(clientEmail))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value(clientPhone))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shippingAddress").value(clientShippingAddress))
                .andDo(MockMvcResultHandlers.print());

        verify(customerService)
                .createCustomer(customerRequest);
    }

}