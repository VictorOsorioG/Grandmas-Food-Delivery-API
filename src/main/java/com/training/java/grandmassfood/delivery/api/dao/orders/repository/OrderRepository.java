package com.training.java.grandmassfood.delivery.api.dao.orders.repository;

import com.training.java.grandmassfood.delivery.api.dao.orders.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);

    @Query("SELECT o.isDelivered FROM Order o WHERE o.uuid = :uuid")
    boolean isDelivered(@Param("uuid") UUID uuid);

    @Query("UPDATE Order o " +
            "SET o.isDelivered = TRUE, o.deliveredAt = :timestamp " +
            "WHERE o.uuid = :uuid")
    @Modifying
    void updateToDelivered(@Param("uuid") UUID uuid, @Param("timestamp") LocalDateTime timestamp);
}
