package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Product;

import java.util.Map;

public class OrderService {
    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao){
        this.orderDao = orderDao;
    }

    public void setOrder(Map<Product, Integer> orderedProducts){
        orderDao.setOrder(orderedProducts);
    }


    public void addToOrder(Product product){
        orderDao.addProduct(product);
    }

    public void removeFromOrder(Product product){
        orderDao.removeProduct(product);
    }

    public void clearOrder(){
        orderDao.clearOrder();
    }

    public Map<Product, Integer> getProductsInCart(){
        return orderDao.getOrderedProducts();
    }

    public int countProduct(Product countedProduct){
        for (Map.Entry<Product, Integer> productEntry : getProductsInCart().entrySet()) {
            if (countedProduct.getName().equals(productEntry.getKey().getName())) return productEntry.getValue();
        }
        return 0;
    }

    public float getAllProductsPrice(Product product){
        return countProduct(product) * product.getDefaultPrice();
    }
}

