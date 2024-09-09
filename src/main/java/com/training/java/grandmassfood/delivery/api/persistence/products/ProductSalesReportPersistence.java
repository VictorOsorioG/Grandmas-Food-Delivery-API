package com.training.java.grandmassfood.delivery.api.persistence.products;

import com.training.java.grandmassfood.delivery.api.dao.products.dto.ProductSales;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductSalesReportPersistence {
    List<ProductSales> getSalesReport(LocalDateTime startDate, LocalDateTime endDate);
}
