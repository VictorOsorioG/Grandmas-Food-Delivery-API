package com.training.java.grandmassfood.delivery.api.dao.orders.repository;

import com.training.java.grandmassfood.delivery.api.dao.orders.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
