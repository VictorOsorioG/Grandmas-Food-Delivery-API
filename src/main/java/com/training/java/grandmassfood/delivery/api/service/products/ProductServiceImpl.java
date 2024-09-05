package com.training.java.grandmassfood.delivery.api.service.products;

import com.training.java.grandmassfood.delivery.api.dao.products.dto.ProductGetResponse;
import com.training.java.grandmassfood.delivery.api.dao.products.dto.ProductRequest;
import com.training.java.grandmassfood.delivery.api.exception.products.ProductNotAvailableComboName;
import com.training.java.grandmassfood.delivery.api.exception.products.ProductNotAvailableException;
import com.training.java.grandmassfood.delivery.api.exception.products.ProductNotFoundException;
import com.training.java.grandmassfood.delivery.api.persistence.products.ProductPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductPersistence productPersistence;

    @Override
    public void productExists(UUID uuid) {
        if (!productPersistence.productExists(uuid)) {
            throw new ProductNotFoundException(uuid);
        }
    }

    @Override
    public void productIsAvailable(UUID uuid) {
        if (!productPersistence.productIsAvailable(uuid)) {
            throw new ProductNotAvailableException(uuid);
        }
    }

    @Override
    public Double getProductPrice(UUID uuid) {
        Double productPrice = productPersistence.getProductPrice(uuid);
        if (Objects.isNull(productPrice)) {
            throw new ProductNotFoundException(uuid);
        }
        return productPrice;
    }

    @Override
    public Long getProductId(UUID productUuid) {
        Long productId = productPersistence.getProductId(productUuid);
        if (Objects.isNull(productId)) {
            throw new ProductNotFoundException(productUuid);
        }
        return productId;
    }

    @Override
    public ProductGetResponse getProductByUuid(UUID uuid) {
        return productPersistence.getProductByUuid(uuid);
    }

    @Override
    public ProductGetResponse createProduct(ProductRequest productRequest) {
        if (!isValidNameFantasy(productRequest.getComboName())) {
            throw new ProductNotAvailableComboName(productRequest.getComboName());
        }
        return productPersistence.createProduct(productRequest);
    }

    private boolean isValidNameFantasy(String comboName){
        if (productPersistence.productExistByComboName(comboName)) {
            throw new ProductNotAvailableComboName(comboName);
        }
        return !productPersistence.productExistByComboName(comboName);
    }










}
