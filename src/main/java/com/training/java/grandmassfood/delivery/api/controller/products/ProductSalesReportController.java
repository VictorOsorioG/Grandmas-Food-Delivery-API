package com.training.java.grandmassfood.delivery.api.controller.products;

import com.training.java.grandmassfood.delivery.api.dao.products.dto.ProductSalesReport;
import com.training.java.grandmassfood.delivery.api.exception.apiresponse.BadRequestApiResponse;
import com.training.java.grandmassfood.delivery.api.service.products.ProductSalesReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("products/sales_report")
@Tag(name = "Product Sales Report Controller")
public class ProductSalesReportController {

    private final ProductSalesReportService productSalesReportService;

    @GetMapping("/{date1}/{date2}")
    @Operation(summary = "Get the product sales report")
    @ResponseStatus(HttpStatus.OK)
    @BadRequestApiResponse
    public ProductSalesReport getSalesReport(@PathVariable String date1, @PathVariable String date2) {
        return productSalesReportService.getSalesReport(date1, date2);
    }
}
