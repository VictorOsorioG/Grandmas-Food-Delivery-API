package com.training.java.grandmassfood.delivery.api.dao.products.repository;

import com.training.java.grandmassfood.delivery.api.dao.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT COUNT(p) > 0 " +
            "FROM Product p " +
            "WHERE p.uuid = :uuid")
    boolean existsByUuid(@Param("uuid") UUID uuid);

    @Query("SELECT p.isAvailable " +
            "FROM Product p " +
            "WHERE p.uuid = :uuid")
    boolean isAvailableByUuid(@Param("uuid") UUID uuid);

    @Query("SELECT p.price " +
            "FROM Product p " +
            "WHERE p.uuid = :uuid")
    Double getPriceByUuid(@Param("uuid") UUID uuid);

    @Query("SELECT p.id " +
            "FROM Product p " +
            "WHERE p.uuid = :uuid")
    Long getIdByUuid(@Param("uuid") UUID productUuid);

    Optional<Product> findByUuid(UUID uuid);

    @Query("SELECT COUNT(p) > 0 " +
            "FROM Product p " +
            "WHERE p.comboName = :comboName")
    boolean existsByComboName(@Param("comboName") String comboName);
}
