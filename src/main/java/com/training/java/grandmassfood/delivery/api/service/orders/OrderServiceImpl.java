package com.training.java.grandmassfood.delivery.api.service.orders;

import com.training.java.grandmassfood.delivery.api.dao.orders.dto.FullOrder;
import com.training.java.grandmassfood.delivery.api.dao.orders.dto.OrderCreatedResponse;
import com.training.java.grandmassfood.delivery.api.dao.orders.dto.OrderRequest;
import com.training.java.grandmassfood.delivery.api.exception.orders.InvalidOrderDeliveredDate;
import com.training.java.grandmassfood.delivery.api.exception.orders.OrderIsDeliveredException;
import com.training.java.grandmassfood.delivery.api.exception.orders.OrderNotFoundException;
import com.training.java.grandmassfood.delivery.api.persistence.orders.OrderPersistence;
import com.training.java.grandmassfood.delivery.api.service.customers.CustomerService;
import com.training.java.grandmassfood.delivery.api.service.orderitems.OrderItemsService;
import com.training.java.grandmassfood.delivery.api.service.products.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final String LOG_PREFIX = "OrderService >>>";

    @Value("${values.iva.value}")
    private double IVA;

    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderItemsService orderItemsService;
    private final OrderPersistence orderPersistence;


    @Override
    public OrderCreatedResponse saveOrder(OrderRequest orderRequest) {
        log.info("{} Validating request", LOG_PREFIX);
        validateOrder(orderRequest.getClientDocument(), orderRequest.getProductUuid());
        FullOrder fullOrder = buildFullOrder(orderRequest);
        log.info("{} Creating order", LOG_PREFIX);
        return orderPersistence.saveOrder(fullOrder);
    }

    @Override
    public OrderCreatedResponse updateOrderToDelivered(UUID uuid, LocalDateTime timestamp) {
        validateOrderAndTimestamp(uuid, timestamp);
        log.info("{} Updating to delivered the order {}", LOG_PREFIX, uuid);
        return orderPersistence.updateOrderToDelivered(uuid, timestamp);
    }

    private void validateOrderAndTimestamp(UUID uuid, LocalDateTime timestamp) {
        orderExists(uuid);
        orderIsDelivered(uuid);
        if (timestamp.isAfter(LocalDateTime.now())) {
            throw new InvalidOrderDeliveredDate(timestamp);
        }
    }

    private void orderExists(UUID uuid) {
        if (!orderPersistence.orderExist(uuid)) {
            throw new OrderNotFoundException(uuid);
        }
    }

    private void orderIsDelivered(UUID uuid) {
        if (orderPersistence.orderIsDelivered(uuid)) {
            throw new OrderIsDeliveredException(uuid);
        }
    }

    private void validateOrder(String clientDocument, UUID productUuid) {
        customerService.clientExists(clientDocument);
        productService.productExists(productUuid);
        productService.productIsAvailable(productUuid);
    }

    private FullOrder buildFullOrder(OrderRequest orderRequest) {
        UUID productUuid = orderRequest.getProductUuid();
        Long customerId = customerService.getCustomerId(orderRequest.getClientDocument());
        Long productId = productService.getProductId(productUuid);
        Double productPrice = productService.getProductPrice(productUuid);
        Double subtotal = orderItemsService.getSubtotal(productPrice, orderRequest.getQuantity());
        Double productIva = calculateIva(subtotal);
        Double total = calculateTotal(subtotal, productIva);
        return FullOrder.builder()
                .uuid(UUID.randomUUID())
                .customerId(customerId)
                .productId(productId)
                .orderDate(LocalDateTime.now())
                .quantity(orderRequest.getQuantity())
                .additionalInfo(orderRequest.getExtraInformation())
                .subtotal(subtotal)
                .iva(productIva)
                .total(total)
                .build();
    }

    private Double calculateTotal(Double subtotal, Double iva) {
        return subtotal + iva;
    }

    private Double calculateIva(Double subtotal) {
        return subtotal * IVA;
    }

}
