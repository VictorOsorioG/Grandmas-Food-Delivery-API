package com.training.java.grandmassfood.delivery.api.persistence.orders;

import com.training.java.grandmassfood.delivery.api.dao.customers.entity.Customer;
import com.training.java.grandmassfood.delivery.api.dao.orderitems.dto.OrderItemsDto;
import com.training.java.grandmassfood.delivery.api.dao.orders.dto.FullOrder;
import com.training.java.grandmassfood.delivery.api.dao.orders.dto.OrderCreatedResponse;
import com.training.java.grandmassfood.delivery.api.dao.orders.entity.Order;
import com.training.java.grandmassfood.delivery.api.dao.orders.repository.OrderRepository;
import com.training.java.grandmassfood.delivery.api.persistence.customers.CustomerPersistence;
import com.training.java.grandmassfood.delivery.api.persistence.orderitems.OrderItemsPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderPersistenceImpl implements OrderPersistence {

    private final OrderRepository orderRepository;
    private final OrderItemsPersistence orderItemsPersistence;
    private final CustomerPersistence customerPersistence;

    @Override
    @Transactional
    public OrderCreatedResponse saveOrder(FullOrder fullOrder) {
        Order order = buildOrder(fullOrder);
        orderRepository.save(order);
        OrderItemsDto orderItemsDto = orderItemsPersistence.saveOrderItems(
                order,
                fullOrder.getProductId(),
                fullOrder.getQuantity(),
                fullOrder.getAdditionalInfo()
        );
        return mapToResponse(order, orderItemsDto) ;
    }

    private OrderCreatedResponse mapToResponse(Order order, OrderItemsDto orderItemsDto) {
        return OrderCreatedResponse.builder()
                .orderUuid(order.getUuid())
                .creationDateTime(order.getOrderDate())
                .clientDocument(order.getCustomer().getDocumentNumber())
                .productUuid(orderItemsDto.getProduct().getUuid())
                .quantity(orderItemsDto.getQuantity())
                .extraInformation(orderItemsDto.getAdditionalInfo())
                .subTotal(order.getSubtotal())
                .tax(order.getIva())
                .grandTotal(order.getTotal())
                .delivered(order.getIsDelivered())
                .deliveredDate(order.getDeliveredAt())
                .build();
    }

    private Order buildOrder(FullOrder fullOrder) {
        Customer customer = customerPersistence.getCustomerReference(fullOrder.getCustomerId());
        return Order.builder()
                .uuid(fullOrder.getUuid())
                .customer(customer)
                .orderDate(fullOrder.getOrderDate())
                .subtotal(fullOrder.getSubtotal())
                .iva(fullOrder.getIva())
                .total(fullOrder.getTotal())
                .isDelivered(false)
                .deliveredAt(null)
                .build();
    }
}
