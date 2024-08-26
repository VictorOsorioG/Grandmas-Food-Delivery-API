package com.training.java.grandmassfood.delivery.api.persistence.orders;

import com.training.java.grandmassfood.delivery.api.dao.orders.dto.FullOrder;
import com.training.java.grandmassfood.delivery.api.dao.orders.dto.OrderCreatedResponse;

public interface OrderPersistence {
    OrderCreatedResponse saveOrder(FullOrder fullOrder);
}
