package com.training.java.grandmassfood.delivery.api.persistence.products;

import com.training.java.grandmassfood.delivery.api.dao.products.dto.ProductSales;
import com.training.java.grandmassfood.delivery.api.dao.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductSalesReportPersistenceImpl implements ProductSalesReportPersistence {

    private static final String LOG_PREFIX = "ProductSalesReportPersistence >>>";

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProductSales> getSalesReport(LocalDateTime startDate, LocalDateTime endDate) {
        log.info("{} Getting productSalesView", LOG_PREFIX);
        return productRepository.getProductSales(startDate, endDate).stream()
                .map(view -> modelMapper.map(view, ProductSales.class))
                .toList();
    }
}
