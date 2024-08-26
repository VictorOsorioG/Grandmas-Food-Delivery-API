package com.training.java.grandmassfood.delivery.api.dao.orders.entity;

import com.training.java.grandmassfood.delivery.api.dao.customers.entity.Customer;
import com.training.java.grandmassfood.delivery.api.dao.products.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "orders")
@Entity
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private UUID uuid;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @Column
    private LocalDateTime orderDate;
    @Column
    private Double subtotal;
    @Column
    private Double iva;
    @Column
    private Double total;
    @Column
    private Boolean isDelivered;
    @Column
    private LocalDateTime deliveredAt;

}
