package com.training.java.grandmassfood.delivery.api.service.orderitems;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderItemsServiceImpl implements OrderItemsService {
    @Override
    public Double getSubtotal(Double productPrice, Integer quantity) {
        return productPrice * quantity;
    }
}
