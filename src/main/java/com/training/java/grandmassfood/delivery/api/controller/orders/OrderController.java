package com.training.java.grandmassfood.delivery.api.controller.orders;

import com.training.java.grandmassfood.delivery.api.dao.orders.dto.OrderCreatedResponse;
import com.training.java.grandmassfood.delivery.api.dao.orders.dto.OrderRequest;
import com.training.java.grandmassfood.delivery.api.exception.apiresponse.BadRequestApiResponse;
import com.training.java.grandmassfood.delivery.api.exception.apiresponse.NotFoundApiResponse;
import com.training.java.grandmassfood.delivery.api.service.orders.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@Tag(name = "Orders Controllers")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "Post in DB and create order from body")
    @NotFoundApiResponse
    @BadRequestApiResponse
    @ResponseStatus(HttpStatus.CREATED)
    public OrderCreatedResponse createOrder(@Valid @RequestBody OrderRequest orderRequest) {
           return orderService.saveOrder(orderRequest);
    }

    @PatchMapping("/{uuid}/delivered/{timestamp}")
    @Operation(summary = "Update delivery status based on UUID and timestamp")
    @NotFoundApiResponse
    @BadRequestApiResponse
    @ResponseStatus(HttpStatus.OK)
    public OrderCreatedResponse updateOrderToDelivered(@PathVariable UUID uuid, @PathVariable LocalDateTime timestamp) {
        return orderService.updateOrderToDelivered(uuid, timestamp);
    }
}
