package com.training.java.grandmassfood.delivery.api.service.products;

import com.training.java.grandmassfood.delivery.api.dao.products.dto.ProductGetResponse;
import com.training.java.grandmassfood.delivery.api.dao.products.dto.ProductRequest;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    void productExists(UUID uuid);
    void productIsAvailable(UUID uuid);
    Double getProductPrice(UUID uuid);
    Long getProductId(UUID productUuid);
    ProductGetResponse getProductByUuid(UUID uuid);
    List<ProductGetResponse> searchProducts(String fantasyName);
    ProductGetResponse createProduct(ProductRequest productRequest);
    void updateProduct(UUID uuid, ProductRequest productRequest);
    void deleteProduct(UUID uuid);
}
