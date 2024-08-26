package com.training.java.grandmassfood.delivery.api.service.orderitems;

public interface OrderItemsService {
    Double getSubtotal(Double productPrice, Integer quantity);
}
