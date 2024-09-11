package com.training.java.grandmassfood.delivery.api.service.products;

import com.training.java.grandmassfood.delivery.api.dao.products.dto.ProductGetResponse;
import com.training.java.grandmassfood.delivery.api.dao.products.dto.ProductRequest;
import com.training.java.grandmassfood.delivery.api.exception.products.*;
import com.training.java.grandmassfood.delivery.api.persistence.products.ProductPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final String LOG_PREFIX = "ProductService >>>";

    private final ProductPersistence productPersistence;

    @Override
    public void productExists(UUID uuid) {
        log.info("{} Checking if product {} exist", LOG_PREFIX, uuid);
        if (!productPersistence.productExists(uuid)) {
            throw new ProductNotFoundException(uuid);
        }
    }

    @Override
    public void productIsAvailable(UUID uuid) {
        log.info("{} Checking if product {} is available", LOG_PREFIX, uuid);
        if (!productPersistence.productIsAvailable(uuid)) {
            throw new ProductNotAvailableException(uuid);
        }
    }

    @Override
    public Double getProductPrice(UUID uuid) {
        log.info("{} Getting product {} price", LOG_PREFIX, uuid);
        Double productPrice = productPersistence.getProductPrice(uuid);
        if (Objects.isNull(productPrice)) {
            throw new ProductNotFoundException(uuid);
        }
        return productPrice;
    }

    @Override
    public Long getProductId(UUID productUuid) {
        log.info("{} Getting product {} id", LOG_PREFIX, productUuid);
        Long productId = productPersistence.getProductId(productUuid);
        if (Objects.isNull(productId)) {
            throw new ProductNotFoundException(productUuid);
        }
        return productId;
    }

    @Override
    public ProductGetResponse getProductByUuid(UUID uuid) {
        log.info("{} Getting product {}", LOG_PREFIX, uuid);
        return productPersistence.getProductByUuid(uuid);
    }

    @Override
    public List<ProductGetResponse> searchProducts(String query) {
        if (query == null || query.trim().isEmpty()) {
            throw new ProductNotSearchQuery();
        }
        log.info("{} Getting products by name: {}", LOG_PREFIX, query);
        List<ProductGetResponse> products = productPersistence.searchProductsByFantasyName(query.trim());
        return products;
    }

    @Override
    public ProductGetResponse createProduct(ProductRequest productRequest) {
        if (!isValidNameFantasy(productRequest.getComboName())) {
            throw new ProductNotAvailableComboName(productRequest.getComboName());
        }
        log.info("{} Saving product", LOG_PREFIX);
        return productPersistence.createProduct(productRequest);
    }

    @Override
    public void updateProduct(UUID uuid, ProductRequest productRequest) {
        productExists(uuid);
        log.info("{} Validating request", LOG_PREFIX);
        ProductGetResponse currentProduct = productPersistence.getProductByUuid(uuid);

        if (isNoDifference(currentProduct, productRequest)) {
            throw new ProductNoContentException();
        }

        if (!currentProduct.getComboName().equals(productRequest.getComboName()) && !isValidNameFantasy(productRequest.getComboName())) {
            throw new ProductNotAvailableComboName(productRequest.getComboName());
        }
        log.info("{} Updating product {}", LOG_PREFIX, uuid);
        productPersistence.updateProduct(uuid, productRequest);
    }

    @Override
    public void deleteProduct(UUID uuid) {
        productExists(uuid);
        log.info("{} Deleting product {}", LOG_PREFIX, uuid);
        productPersistence.deleteProduct(uuid);
    }

    private boolean isValidNameFantasy(String comboName){
        if (productPersistence.productExistByComboName(comboName)) {
            throw new ProductNotAvailableComboName(comboName);
        }
        return !productPersistence.productExistByComboName(comboName);
    }

    private boolean isNoDifference(ProductGetResponse currentProduct, ProductRequest newProductRequest) {
        return Objects.equals(currentProduct.getComboName(), newProductRequest.getComboName()) &&
                Objects.equals(currentProduct.getCategory(), newProductRequest.getCategory()) &&
                Objects.equals(currentProduct.getDescription(), newProductRequest.getDescription()) &&
                Objects.equals(currentProduct.getPrice(), newProductRequest.getPrice()) &&
                Objects.equals(currentProduct.getIsAvailable(), newProductRequest.getIsAvailable());
    }









}
