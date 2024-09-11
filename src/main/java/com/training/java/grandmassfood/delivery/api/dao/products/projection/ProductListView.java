package com.training.java.grandmassfood.delivery.api.dao.products.projection;

import com.training.java.grandmassfood.delivery.api.dao.products.entity.Category;

public interface ProductListView {
    Category getCategory();
    String getFantasyName();
    double getPriceWithTax();
}
