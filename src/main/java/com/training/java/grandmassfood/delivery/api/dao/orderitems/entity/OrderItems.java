package com.training.java.grandmassfood.delivery.api.dao.orderitems.entity;

import com.training.java.grandmassfood.delivery.api.dao.orders.entity.Order;
import com.training.java.grandmassfood.delivery.api.dao.products.entity.Product;
import jakarta.persistence.*;
import lombok.*;

@Data
@Table(name = "order_items")
@Entity
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderItems {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @Column
    private Integer quantity;
    @Column
    private String additionalInfo;
    @Column
    private Double price;

}
