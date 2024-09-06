package com.training.java.grandmassfood.delivery.api.persistence.products;

import com.training.java.grandmassfood.delivery.api.dao.products.dto.ProductGetResponse;
import com.training.java.grandmassfood.delivery.api.dao.products.dto.ProductRequest;
import com.training.java.grandmassfood.delivery.api.dao.products.entity.Product;

import java.util.UUID;

public interface ProductPersistence {
    boolean productExists(UUID uuid);
    boolean productIsAvailable(UUID uuid);
    Double getProductPrice(UUID uuid);
    Long getProductId(UUID productUuid);
    Product getProductReference(Long productId);
    ProductGetResponse getProductByUuid(UUID uuid);
    ProductGetResponse createProduct(ProductRequest productRequest);
    void updateProduct(UUID uuid, ProductRequest productRequest);
    boolean productExistByComboName(String comboName);
    void deleteProduct(UUID uuid);
}
