package com.training.java.grandmassfood.delivery.api.service.orders;

import com.training.java.grandmassfood.delivery.api.dao.orders.dto.FullOrder;
import com.training.java.grandmassfood.delivery.api.dao.orders.dto.OrderCreatedResponse;
import com.training.java.grandmassfood.delivery.api.dao.orders.dto.OrderRequest;
import com.training.java.grandmassfood.delivery.api.persistence.orders.OrderPersistence;
import com.training.java.grandmassfood.delivery.api.service.customers.CustomerService;
import com.training.java.grandmassfood.delivery.api.service.orderitems.OrderItemsService;
import com.training.java.grandmassfood.delivery.api.service.products.ProductService;
import jakarta.annotation.security.RunAs;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private CustomerService customerService;
    @Mock
    private ProductService productService;
    @Mock
    private OrderItemsService orderItemsService;
    @Mock
    private OrderPersistence orderPersistence;

    @Test
    void saveOrder() {
        Double productPrice = 9.99;
        int quantity = 2;
        OrderRequest orderRequest = OrderRequest.builder()
                .clientDocument("CC-123456789")
                .extraInformation("No onions")
                .productUuid(UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d481"))
                .quantity(quantity)
                .build();

        FullOrder fullOrder = FullOrder.builder()
                .uuid(UUID.randomUUID())
                .iva(1.0)
                .total(1.0)
                .isDelivered(false)
                .subtotal(1.0)
                .build();

        OrderCreatedResponse orderCreated = OrderCreatedResponse.builder()
                .orderUuid(UUID.randomUUID())
                .clientDocument("hjkgd")
                .build();

        /*
        Mockito.when(customerService.getCustomerId())
                .thenReturn(1L);
        Mockito.when(productService.getProductPrice())
                .thenReturn(productPrice);

         */
        Mockito.when(orderItemsService.getSubtotal(productPrice, quantity))
                .thenReturn(productPrice*quantity);
        Mockito.when(orderPersistence.saveOrder(Mockito.any(FullOrder.class)))
                .thenReturn(orderCreated);


        OrderCreatedResponse orderCreatedResponse = orderService.saveOrder(orderRequest);

        System.out.println(orderCreatedResponse);
    }
}