package com.training.java.grandmassfood.delivery.api.service.products;

import com.training.java.grandmassfood.delivery.api.dao.products.dto.ProductSalesReport;

public interface ProductSalesReportService {

    ProductSalesReport getSalesReport(String date1, String date2);
}
