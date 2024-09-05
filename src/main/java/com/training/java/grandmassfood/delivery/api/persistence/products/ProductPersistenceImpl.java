package com.training.java.grandmassfood.delivery.api.persistence.products;

import com.training.java.grandmassfood.delivery.api.dao.products.dto.ProductGetResponse;
import com.training.java.grandmassfood.delivery.api.dao.products.dto.ProductRequest;
import com.training.java.grandmassfood.delivery.api.dao.products.entity.Product;
import com.training.java.grandmassfood.delivery.api.dao.products.repository.ProductRepository;
import com.training.java.grandmassfood.delivery.api.exception.products.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductPersistenceImpl implements ProductPersistence {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

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

    @Override
    @Transactional(readOnly = true)
    public ProductGetResponse getProductByUuid(UUID uuid) {
        Optional<Product> optionalProduct = productRepository.findByUuid(uuid);
        return optionalProduct.map(this::mapToResponse)
                .orElseThrow(() -> new ProductNotFoundException(uuid));
    }

    @Override
    public ProductGetResponse createProduct(ProductRequest productRequest) {
        Product product = modelMapper.map(productRequest,Product.class);
        product.setUuid(UUID.randomUUID());
        product.setCreatedAt(LocalDateTime.now());
        String comboName = productRequest.getComboName().toUpperCase();
        product.setComboName(comboName);
        productRepository.save(product);
        return modelMapper.map(product,ProductGetResponse.class);
    }

    @Override
    public boolean productExistByComboName(String comboName) {
        return productRepository.existsByComboName(comboName);
    }

    private ProductGetResponse mapToResponse(Product product) {
        return ProductGetResponse.builder()
                .productUuid(product.getUuid())
                .comboName(product.getComboName())
                .category(product.getCategory())
                .description(product.getDescription())
                .price(product.getPrice())
                .isAvailable(product.getIsAvailable())
                .build();
    }

}
