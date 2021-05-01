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


    public void addToOrder(Product product){
        cartDao.addToCart(product);
    }

    public void removeFromOrder(Product product){
        cartDao.removeFromCart(product);
    }

    public void clearOrder(){
        cartDao.clearCart();
    }

    public Map<Product, Integer> getProductsInCart(){
        return cartDao.getProductsInCart();
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


//    public void addToCart(Product product){
//        cartDao.addToCart(product);
//    }
//
//    public void removeFromCart(Product product){
//        cartDao.removeFromCart(product);
//    }
//
//    public void clearCart(){
//        cartDao.clearCart();
//    }
//
//    public Map<Product, Integer> getProductsInCart(){
//        return cartDao.getProductsInCart();
//    }
//
//    public int countProduct(Product countedProduct){
//        for (Map.Entry<Product, Integer> productEntry : getProductsInCart().entrySet()) {
//            if (countedProduct.getName().equals(productEntry.getKey().getName())) return productEntry.getValue();
//        }
//        return 0;
//    }
//
//    public float getAllProductsPrice(Product product){
//        return countProduct(product) * product.getDefaultPrice();
//    }
