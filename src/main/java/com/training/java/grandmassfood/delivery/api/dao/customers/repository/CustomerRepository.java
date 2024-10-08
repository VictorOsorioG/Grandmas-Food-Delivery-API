package com.training.java.grandmassfood.delivery.api.dao.customers.repository;

import com.training.java.grandmassfood.delivery.api.dao.customers.entity.Customer;
import com.training.java.grandmassfood.delivery.api.dao.customers.projection.CustomerListView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    Optional<Customer> findByDocumentNumber(String clientDocument);

    @Query("UPDATE Customer c " +
            "SET c.documentNumber = :documentNumber, " +
            "c.fullName = :fullName, " +
            "c.email = :email, " +
            "c.phoneNumber = :phoneNumber, " +
            "c.shippingAddress = :shippingAddress " +
            "WHERE c.documentNumber = :clientDocument")
    @Modifying
    void update(@Param("clientDocument") String clientDocument,
                @Param("documentNumber") String documentNumber,
                @Param("fullName") String fullName,
                @Param("email") String email,
                @Param("phoneNumber") String phoneNumber,
                @Param("shippingAddress") String shippingAddress
    );

    @Query("DELETE FROM Customer c " +
            "WHERE c.documentNumber = :clientDocument")
    @Modifying
    void deleteByDocumentNumber(@Param("clientDocument") String clientDocument);
        @Query("SELECT c FROM Customer c ORDER BY " +
                "CASE WHEN :orderBy = 'DOCUMENT' THEN c.documentNumber " +
                "     WHEN :orderBy = 'NAME' THEN c.fullName " +
                "     WHEN :orderBy = 'ADDRESS' THEN c.shippingAddress " +
                "     END "+
                "ASC")
        List<CustomerListView> getListCustomerByAsc(@Param("orderBy") String orderBy);
        @Query("SELECT c FROM Customer c ORDER BY " +
                "CASE WHEN :orderBy = 'DOCUMENT' THEN c.documentNumber " +
                "     WHEN :orderBy = 'NAME' THEN c.fullName " +
                "     WHEN :orderBy = 'ADDRESS' THEN c.shippingAddress " +
                "     END "+
                "DESC")
        List<CustomerListView> getListCustomerByDesc(@Param("orderBy") String orderBy);
}
