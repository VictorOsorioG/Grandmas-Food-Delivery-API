package com.training.java.grandmassfood.delivery.api.service.orders;

import com.training.java.grandmassfood.delivery.api.persistence.orders.OrderPersistence;
import com.training.java.grandmassfood.delivery.api.service.customers.CustomerService;
import com.training.java.grandmassfood.delivery.api.service.orderitems.OrderItemsService;
import com.training.java.grandmassfood.delivery.api.service.products.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


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

    }
}