package com.training.java.grandmassfood.delivery.api.controller.orders;

import com.training.java.grandmassfood.delivery.api.dao.orders.dto.OrderCreatedResponse;
import com.training.java.grandmassfood.delivery.api.dao.orders.dto.OrderRequest;
import com.training.java.grandmassfood.delivery.api.service.orders.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderCreatedResponse createOrder(@Valid @RequestBody OrderRequest orderRequest) {
           return orderService.saveOrder(orderRequest);
    }

    @PatchMapping("/{uuid}/delivered/{timestamp}")
    public OrderCreatedResponse updateOrderToDelivered(@PathVariable UUID uuid, @PathVariable LocalDateTime timestamp) {
        return orderService.updateOrderToDelivered(uuid, timestamp);
    }
}
