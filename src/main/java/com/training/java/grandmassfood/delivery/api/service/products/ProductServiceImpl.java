package com.training.java.grandmassfood.delivery.api.service.products;

import com.training.java.grandmassfood.delivery.api.dao.products.dto.ProductGetResponse;
import com.training.java.grandmassfood.delivery.api.dao.products.dto.ProductRequest;
import com.training.java.grandmassfood.delivery.api.exception.products.ProductNoContentException;
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

    @Override
    public void updateProduct(UUID uuid, ProductRequest productRequest) {
        productExists(uuid);

        ProductGetResponse currentProduct = productPersistence.getProductByUuid(uuid);

        if (isNoDifference(currentProduct, productRequest)) {
            throw new ProductNoContentException();
        }

        if (!currentProduct.getComboName().equals(productRequest.getComboName()) && !isValidNameFantasy(productRequest.getComboName())) {
            throw new ProductNotAvailableComboName(productRequest.getComboName());
        }

        productPersistence.updateProduct(uuid, productRequest);
    }

    @Override
    public void deleteProduct(UUID uuid) {
        productExists(uuid);
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
