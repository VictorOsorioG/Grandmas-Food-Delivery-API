package com.training.java.grandmassfood.delivery.api.dao.customers.repository;

import com.training.java.grandmassfood.delivery.api.dao.customers.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT COUNT(c) > 0 " +
            "FROM Customer c " +
            "WHERE c.documentNumber = :clientDocument")
    boolean existsByDocumentNumber(@Param("clientDocument") String clientDocument);

    @Query("SELECT c.id " +
            "FROM Customer c " +
            "WHERE c.documentNumber = :clientDocument")
    Long getIdByDocumentNumber(@Param("clientDocument") String clientDocument);
}
