package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Product;

import javax.servlet.http.HttpServletRequest;
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

    public void setUserDetails(HttpServletRequest req){
        orderDao.setUserDetails(req);
    }

    public float getAllProductsPrice(Product product){
        return countProduct(product) * product.getDefaultPrice();
    }

    public float getAllOrderPrice(){
        float price = 0;
        for (Map.Entry<Product, Integer> productEntry : orderDao.getOrderedProducts().entrySet()) {
            price += getAllProductsPrice(productEntry.getKey());
        }
        return price;
    }

    public Map<String, String> getBillingDetails(){
        return orderDao.getBillingDetails();
    }

    public Map<String, String> getShipmentDetails(){
        return orderDao.getShipmentDetails();
    }
}

