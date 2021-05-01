package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoMem implements OrderDao {

    private List<Order> data = new ArrayList<>();
    private static OrderDaoMem instance = null;


    private OrderDaoMem() {
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }


    public void add(Order order) {
        order.setId(data.size() + 1);
        data.add(order);
    }


    public Order find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }


    public void remove(int id) {
        data.remove(find(id));
    }


    public List<Order> getAll() {
        return data;
    }
}




//public class OrderDaoMem implements OrderDao {
//
//    private static OrderDaoMem instance = null;
//    private static Order order = new Order();
//
//    private OrderDaoMem(){}
//
//    public static OrderDaoMem getInstance(){
//        if (instance == null){
//            instance = new OrderDaoMem();
//        }
//        return instance;
//    }
//
//
//    @Override
//    public void addProduct(Product product) {
//        order.addProduct(product);
//    }
//
//    @Override
//    public void removeProduct(Product product) {
//        order.removeProduct(product);
//    }
//
//    @Override
//    public void clearOrder() {
//        order.clearOrder();
//    }
//
//    @Override
//    public Map<Product, Integer> getOrderedProducts() {
//        return order.getOrderedProducts();
//    }
//}