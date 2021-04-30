package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import java.util.Map;

public class OrderDaoMem implements OrderDao {

    private static OrderDaoMem instance = null;
    private static Order order = new Order();

    private OrderDaoMem(){}

    public static OrderDaoMem getInstance(){
        if (instance == null){
            instance = new OrderDaoMem();
        }
        return instance;
    }


    @Override
    public void addProduct(Product product) {
        order.addProduct(product);
    }

    @Override
    public void removeProduct(Product product) {
        order.removeProduct(product);
    }

    @Override
    public void clearOrder() {
        order.clearOrder();
    }

    @Override
    public Map<Product, Integer> getOrderedProducts() {
        return order.getOrderedProducts();
    }
}
