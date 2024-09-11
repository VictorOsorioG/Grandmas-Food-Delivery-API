package com.training.java.grandmassfood.delivery.api.service.menu;

import com.training.java.grandmassfood.delivery.api.dao.products.dto.menu.ProductList;
import com.training.java.grandmassfood.delivery.api.dao.products.dto.menu.ProductListAvailableResponse;
import com.training.java.grandmassfood.delivery.api.persistence.menu.ProductListAvailablePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductListAvailableServiceImpl implements ProductListAvailableService{
    private final ProductListAvailablePersistence productListAvailablePersistence;

    @Override
    public ProductListAvailableResponse getListProductsAvailableText() {
        List<ProductList> productLists = productListAvailablePersistence.getListProducts();
        return ProductListAvailableResponse.builder()
                .productLists(productLists)
                .build();
    }

    @Override
    public ProductListAvailableResponse getListProductsAvailablePdf(String pdf) {
        return null;
    }

    @Override
    public ProductListAvailableResponse getListProductsAvailableWord(String word) {
        return null;
    }
}
