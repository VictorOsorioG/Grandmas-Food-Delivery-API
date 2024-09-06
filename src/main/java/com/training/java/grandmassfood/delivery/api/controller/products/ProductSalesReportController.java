package com.training.java.grandmassfood.delivery.api.controller.products;

import com.training.java.grandmassfood.delivery.api.dao.products.dto.ProductSalesReport;
import com.training.java.grandmassfood.delivery.api.service.products.ProductSalesReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("products/sales_report")
@Tag(name = "Product Sales Report Controller")
public class ProductSalesReportController {

    private final ProductSalesReportService productSalesReportService;

    @GetMapping("/{date1}/{date2}")
    @Operation(summary = "Get the product sales report")
    public ProductSalesReport getSalesReport(@PathVariable String date1, @PathVariable String date2) {
        return productSalesReportService.getSalesReport(date1, date2);
    }
}
