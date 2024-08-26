package com.training.java.grandmassfood.delivery.api.dao.orderitems.repository;

import com.training.java.grandmassfood.delivery.api.dao.orderitems.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
}
