package com.training.java.grandmassfood.delivery.api.service.products;

import com.training.java.grandmassfood.delivery.api.dao.products.dto.ProductSales;
import com.training.java.grandmassfood.delivery.api.dao.products.dto.ProductSalesReport;
import com.training.java.grandmassfood.delivery.api.exception.salesreport.InvalidDateRange;
import com.training.java.grandmassfood.delivery.api.exception.salesreport.InvalidSalesReportDateFormat;
import com.training.java.grandmassfood.delivery.api.persistence.products.ProductSalesReportPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductSalesReportServiceImpl implements ProductSalesReportService {

    private static final String LOG_PREFIX = "ProductSalesReportService >>>";

    private final ProductSalesReportPersistence productSalesReportPersistence;

    @Override
    public ProductSalesReport getSalesReport(String date1, String date2) {
        log.info("{} Validate dates {}, {}", LOG_PREFIX, date1, date2);
        validateDates(date1, date2);
        LocalDateTime dateTime1 = mapDateToLocalDate(date1);
        LocalDateTime dateTime2 = mapDateToLocalDate(date2);
        validateRange(dateTime1, dateTime2);
        log.info("{} Getting sales report", LOG_PREFIX);
        List<ProductSales> productSales = productSalesReportPersistence.getSalesReport(dateTime1, dateTime2);
        return ProductSalesReport.builder()
                .bestSales(findSales(productSales, Comparator.naturalOrder()))
                .worseSales(findSales(productSales, Comparator.reverseOrder()))
                .productSales(productSales)
                .build();
    }

    private void validateDates(String date1, String date2) {
        validDateFormat(date1);
        validDateFormat(date2);
    }

    private void validDateFormat(String date) {
        if (!date.matches("^\\d{4}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])$")) {
            throw new InvalidSalesReportDateFormat(date);
        }
    }

    private LocalDateTime mapDateToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd")).atStartOfDay();
    }

    private void validateRange(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        if (dateTime1.isAfter(dateTime2)) {
            throw new InvalidDateRange(dateTime1, dateTime2);
        }
    }

    private List<String> findSales(List<ProductSales> productSales, Comparator<Integer> comparator) {
        int targetSale = productSales.stream()
                .map(ProductSales::getQuantity)
                .max(comparator)
                .orElse(0);
        return productSales.stream()
                .filter(productSale -> productSale.getQuantity() == targetSale)
                .map(ProductSales::getComboName)
                .toList();
    }

}
