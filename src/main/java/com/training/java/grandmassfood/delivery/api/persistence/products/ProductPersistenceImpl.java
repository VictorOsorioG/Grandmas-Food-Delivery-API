package com.training.java.grandmassfood.delivery.api.persistence.products;

import com.training.java.grandmassfood.delivery.api.dao.products.entity.Product;
import com.training.java.grandmassfood.delivery.api.dao.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductPersistenceImpl implements ProductPersistence {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public boolean productExists(UUID uuid) {
        return productRepository.existsByUuid(uuid);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean productIsAvailable(UUID uuid) {
        return productRepository.isAvailableByUuid(uuid);
    }

    @Override
    @Transactional(readOnly = true)
    public Double getProductPrice(UUID uuid) {
        return productRepository.getPriceByUuid(uuid);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getProductId(UUID productUuid) {
        return productRepository.getIdByUuid(productUuid);
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProductReference(Long productId) {
        return productRepository.getReferenceById(productId);
    }
}
