package com.training.java.grandmassfood.delivery.api.service.menu;

import com.training.java.grandmassfood.delivery.api.dao.products.dto.menu.ProductListAvailableResponse;

public interface ProductListAvailableService {
    ProductListAvailableResponse getListProductsAvailableText();
    ProductListAvailableResponse getListProductsAvailablePdf(String pdf);
    ProductListAvailableResponse getListProductsAvailableWord(String word);
}
