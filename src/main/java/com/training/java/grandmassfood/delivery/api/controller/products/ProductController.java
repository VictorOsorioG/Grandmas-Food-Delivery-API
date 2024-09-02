package com.training.java.grandmassfood.delivery.api.controller.products;


import com.training.java.grandmassfood.delivery.api.dao.products.dto.ProductGetResponse;
import com.training.java.grandmassfood.delivery.api.service.products.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    @GetMapping("/{uuid}")
    public ProductGetResponse getProduct(@PathVariable UUID uuid) {
        return productService.getProductByUuid(uuid);
    }
}
