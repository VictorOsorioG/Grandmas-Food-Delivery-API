package com.training.java.grandmassfood.delivery.api.persistence.orders;

import com.training.java.grandmassfood.delivery.api.dao.orders.dto.FullOrder;
import com.training.java.grandmassfood.delivery.api.dao.orders.dto.OrderCreatedResponse;

import java.time.LocalDateTime;
import java.util.UUID;

public interface OrderPersistence {
    OrderCreatedResponse saveOrder(FullOrder fullOrder);

    OrderCreatedResponse updateOrderToDelivered(UUID uuid, LocalDateTime timestamp);

    boolean orderExist(UUID uuid);

    boolean orderIsDelivered(UUID uuid);
}
