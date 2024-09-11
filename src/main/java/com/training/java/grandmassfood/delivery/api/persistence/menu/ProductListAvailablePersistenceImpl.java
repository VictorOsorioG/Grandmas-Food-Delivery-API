package com.training.java.grandmassfood.delivery.api.persistence.menu;

import com.training.java.grandmassfood.delivery.api.dao.products.dto.menu.ProductList;
import com.training.java.grandmassfood.delivery.api.dao.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductListAvailablePersistenceImpl implements ProductListAvailablePersistence{
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    @Value("${values.iva.value}")
    private double IVA;
    @Override
    @Transactional(readOnly = true)
    public List<ProductList> getListProducts() {
        return productRepository.getListProductByCategory().stream()
                .map(view -> modelMapper.map(view, ProductList.class))
                .toList();
    }
}
