package com.codecool.shop.dao;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface OrderDao {
    void setOrder(Map<Product, Integer> order);
    void addProduct(Product product);
    void removeProduct(Product product);
    void clearOrder();
    Map<Product, Integer> getOrderedProducts();
    void setUserDetails(HttpServletRequest req);
    Map<String, String> getBillingDetails();
    Map<String, String> getShipmentDetails();
}
