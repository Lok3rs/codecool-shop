package com.codecool.shop.dao;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import java.util.List;
import java.util.Map;

public interface OrderDao {
//    void addProduct(Product product);
//    void removeProduct(Product product);
//    void clearOrder();
//    Map<Product, Integer> getOrderedProducts();
    void add(Order order);
    Order find(int id);
    void remove(int id);
    List<Order> getAll();
}
