package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
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
    public void setOrder(Map<Product, Integer> orderedProducts) {
        order.setOrderedProducts(orderedProducts);
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

    @Override
    public void setUserDetails() {

    }

    private void setUserFirstName(String firstName){
        order.setUserFirstName(firstName);
    }

    private void setUserLastName(String lastName){
        order.setUserLastName(lastName);
    }

    private void setUserEmail(String email){
        order.setUserMail(email);
    }

    private void setUserPhone(int phoneNumber){
        order.setUserPhone(phoneNumber);
    }

    private void setCountryBilling(String country){
        order.setCountryBilling(country);
    }

    private void setCityBilling(String city){
        order.setCityBilling(city);
    }

    private void setZipBilling(String zipCode){
        order.setZipBilling(zipCode);
    }

    private void setStreetBilling(String streetName){
        order.setStreetBilling(streetName);
    }

    private void setHouseNumberBilling(int houseNumber){
        order.setHouseNumberBilling(houseNumber);
    }
    private void setCountryShip(String country){
        order.setCountryShip(country);
    }

    private void setCityShip(String city){
        order.setCityShip(city);
    }

    private void setZipShip(String zipCode){
        order.setZipShip(zipCode);
    }

    private void setStreetShip(String streetName){
        order.setStreetShip(streetName);
    }

    private void setHouseNumberShip(int houseNumber){
        order.setHouseNumberShip(houseNumber);
    }


}