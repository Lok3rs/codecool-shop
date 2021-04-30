package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.List;
import java.util.Map;

public interface CartDao {
    void addToCart(Product product);
    void removeFromCart(Product product);
    void clearCart();
    Map<Product, Integer> getProductsInCart();
}
