package com.training.java.grandmassfood.delivery.api.controller.products;


import com.training.java.grandmassfood.delivery.api.dao.products.dto.ProductGetResponse;
import com.training.java.grandmassfood.delivery.api.service.products.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Tag(name = "Product Controllers")
public class ProductController {
    private final ProductService productService;
    @GetMapping("/{uuid}")
    public ProductGetResponse getProduct(@PathVariable UUID uuid) {
        return productService.getProductByUuid(uuid);
    }
}
