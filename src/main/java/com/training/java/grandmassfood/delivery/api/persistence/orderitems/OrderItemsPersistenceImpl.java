package com.training.java.grandmassfood.delivery.api.persistence.orderitems;

import com.training.java.grandmassfood.delivery.api.dao.orderitems.dto.OrderItemsDto;
import com.training.java.grandmassfood.delivery.api.dao.orderitems.entity.OrderItems;
import com.training.java.grandmassfood.delivery.api.dao.orderitems.repository.OrderItemsRepository;
import com.training.java.grandmassfood.delivery.api.dao.orders.entity.Order;
import com.training.java.grandmassfood.delivery.api.persistence.products.ProductPersistenceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderItemsPersistenceImpl implements OrderItemsPersistence {

    private final OrderItemsRepository orderItemsRepository;
    private final ProductPersistenceImpl productPersistence;

    @Override
    @Transactional
    public OrderItemsDto saveOrderItems(Order order, Long productId, int quantity, String additionalInfo) {
        OrderItems orderItems = buildOrderItems(order, productId, quantity, additionalInfo);
        orderItemsRepository.save(orderItems);
        return mapOrderItemsToDto(orderItems);
    }

    private OrderItemsDto mapOrderItemsToDto(OrderItems orderItems) {
        return OrderItemsDto.builder()
                .product(orderItems.getProduct())
                .quantity(orderItems.getQuantity())
                .additionalInfo(orderItems.getAdditionalInfo())
                .price(orderItems.getPrice())
                .build();
    }

    private OrderItems buildOrderItems(Order order, Long productId, int quantity, String additionalInfo) {

        return OrderItems.builder()
                .order(order)
                .product(productPersistence.getProductReference(productId))
                .quantity(quantity)
                .additionalInfo(additionalInfo)
                .price(order.getSubtotal())
                .build();
    }
}
