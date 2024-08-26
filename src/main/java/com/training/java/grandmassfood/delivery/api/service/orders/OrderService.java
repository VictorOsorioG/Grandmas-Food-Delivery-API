package com.training.java.grandmassfood.delivery.api.service.orders;

import com.training.java.grandmassfood.delivery.api.dao.orders.dto.OrderCreatedResponse;
import com.training.java.grandmassfood.delivery.api.dao.orders.dto.OrderRequest;

public interface OrderService {
    OrderCreatedResponse saveOrder(OrderRequest orderRequest);
}
