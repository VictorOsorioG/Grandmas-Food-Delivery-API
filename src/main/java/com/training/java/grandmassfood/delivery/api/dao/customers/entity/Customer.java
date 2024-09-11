package com.training.java.grandmassfood.delivery.api.dao.customers.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Table(name = "customers")
@Entity
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documentNumber;
    @Column
    private String fullName;
    @Column
    private String email;
    @Column
    private String phoneNumber;
    @Column
    private String shippingAddress;
    @Column
    private LocalDateTime createdAt;
}
