package com.training.java.grandmassfood.delivery.api.persistence.orderitems;

import com.training.java.grandmassfood.delivery.api.dao.orderitems.dto.OrderItemsDto;
import com.training.java.grandmassfood.delivery.api.dao.orders.entity.Order;

public interface OrderItemsPersistence {
    OrderItemsDto saveOrderItems(Order order, Long productId, int quantity, String additionalInfo);
}

