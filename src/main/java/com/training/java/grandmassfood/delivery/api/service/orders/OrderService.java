package com.training.java.grandmassfood.delivery.api.service.orders;

import com.training.java.grandmassfood.delivery.api.dao.orders.dto.OrderCreatedResponse;
import com.training.java.grandmassfood.delivery.api.dao.orders.dto.OrderRequest;

import java.time.LocalDateTime;
import java.util.UUID;

public interface OrderService {
    OrderCreatedResponse saveOrder(OrderRequest orderRequest);

    OrderCreatedResponse updateOrderToDelivered(UUID uuid, LocalDateTime timestamp);
}
