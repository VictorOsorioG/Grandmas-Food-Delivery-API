package com.training.java.grandmassfood.delivery.api.controller.products;


import com.training.java.grandmassfood.delivery.api.dao.products.dto.ProductGetResponse;
import com.training.java.grandmassfood.delivery.api.dao.products.dto.ProductRequest;
import com.training.java.grandmassfood.delivery.api.dao.products.entity.Product;
import com.training.java.grandmassfood.delivery.api.dao.products.repository.ProductRepository;
import com.training.java.grandmassfood.delivery.api.service.products.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Tag(name = "Product Controllers")
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;
    @GetMapping("/{uuid}")
    @Operation(summary = "Get the products by uuid")
    public ProductGetResponse getProduct(@PathVariable UUID uuid) {
        return productService.getProductByUuid(uuid);
    }
    @PostMapping
    @Operation(summary = "Post in DB and create product from body")
    public ProductGetResponse createProduct(@Valid @RequestBody ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }
}
