package com.training.java.grandmassfood.delivery.api.persistence.menu;

import com.training.java.grandmassfood.delivery.api.dao.products.dto.menu.ProductList;

import java.util.List;

public interface ProductListAvailablePersistence {
    List<ProductList> getListProducts();
}
