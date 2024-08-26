package com.training.java.grandmassfood.delivery.api.dao.products.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table(name = "products")
@Entity
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private UUID uuid;
    @Column
    private Category category;
    @Column
    private String comboName;
    @Column
    private String description;
    @Column
    private Double price;
    @Column
    private Boolean isAvailable;
    @Column
    private LocalDateTime createdAt;
}
