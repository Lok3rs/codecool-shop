package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.Map;

public interface OrderDao {
    void addProduct(Product product);
    void removeProduct(Product product);
    void clearOrder();
    Map<Product, Integer> getOrderedProducts();
}
