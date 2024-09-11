package com.training.java.grandmassfood.delivery.api.dao.products.repository;

import com.training.java.grandmassfood.delivery.api.dao.products.entity.Category;
import com.training.java.grandmassfood.delivery.api.dao.products.entity.Product;
import com.training.java.grandmassfood.delivery.api.dao.products.projection.ProductListView;
import com.training.java.grandmassfood.delivery.api.dao.products.projection.ProductSalesView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
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

    @Query("UPDATE Product p " +
            "SET p.comboName = :comboName, " +
            "p.category = :category, " +
            "p.description = :description, " +
            "p.price = :price, " +
            "p.isAvailable = :isAvailable " +
            "WHERE p.uuid = :uuid")
    @Modifying
    void update(@Param("uuid") UUID uuid,
                @Param("comboName") String comboName,
                @Param("category") Category category,
                @Param("description") String description,
                @Param("price") Double price,
                @Param("isAvailable") Boolean isAvailable);

    @Query("DELETE FROM Product p " +
            "WHERE p.uuid = :uuid")
    @Modifying
    void deleteProductByUuid(@Param("uuid") UUID uuid);

    @Query("SELECT p.comboName AS comboName, SUM(oi.quantity) AS quantity, SUM(oi.quantity * oi.price) AS grossSales " +
            "FROM Order o " +
            "INNER JOIN o.orderItems oi " +
            "INNER JOIN oi.product p " +
            "WHERE o.orderDate BETWEEN :startDate AND :endDate " +
            "GROUP BY p.id, p.comboName " +
            "HAVING SUM(oi.quantity * oi.price) > 0")
    List<ProductSalesView> getProductSales(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT p " +
           "FROM Product p " +
           "WHERE LOWER(p.comboName) LIKE LOWER(CONCAT('%', :fantasyName, '%')) " +
           "ORDER BY p.comboName ASC")
    List<Product> findByFantasyNameContainingIgnoreCaseOrderByComboNameAsc(@Param("fantasyName") String fantasyName);

    @Query("SELECT p.category AS category, p.comboName AS fantasyName, (p.price * 0.19 + p.price) AS priceWithTax " +
            "FROM Product p " +
            "WHERE p.isAvailable = true " +
            "ORDER BY p.category, p.comboName")
    List<ProductListView> getListProductByCategory();
}
