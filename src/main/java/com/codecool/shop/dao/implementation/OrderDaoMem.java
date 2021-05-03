package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
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
    public Order getOrder(){ return order; }

    @Override
    public boolean getorderPaidStatus(){ return order.isDonePayment(); }

    @Override
    public void orderPaidStatus(boolean status){ order.setDonePayment(status); }

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
    public void setUserDetails(HttpServletRequest req) {
        setUserFirstName(req.getParameter("first-name"));
        setUserLastName(req.getParameter("last-name"));
        setUserEmail(req.getParameter("email"));
        setUserPhone(Integer.parseInt(req.getParameter("phone")));
        setCountryBilling(req.getParameter("country"));
        setCityBilling(req.getParameter("city"));
        setZipBilling(req.getParameter("zip"));
        setStreetBilling(req.getParameter("street"));
        setHouseNumberBilling(Integer.parseInt(req.getParameter("house-number")));

        setCountryShip(req.getParameter("country-ship"));
        setCityShip(req.getParameter("city-ship"));
        setStreetShip(req.getParameter("city-ship"));
        setZipShip(req.getParameter("zip-ship"));
        setHouseNumberShip(Integer.parseInt(req.getParameter("house-number-ship")));
    }

    @Override
    public Map<String, String> getBillingDetails() {
        Map<String, String> billingDetails = new LinkedHashMap<>();
        billingDetails.put("First name", order.getUserFirstName());
        billingDetails.put("Last name", order.getUserLastName());
        billingDetails.put("Email", order.getUserMail());
        billingDetails.put("Phone", String.valueOf(order.getUserPhone()));
        billingDetails.put("Country", order.getCountryBilling());
        billingDetails.put("City", order.getCityBilling());
        billingDetails.put("Zip", order.getZipBilling());
        billingDetails.put("Street", order.getStreetBilling());
        billingDetails.put("House number", String.valueOf(order.getHouseNumberBilling()));
        return billingDetails;
    }

    @Override
    public Map<String, String> getShipmentDetails() {
        Map<String, String> shipmentDetails = new LinkedHashMap<>();
        shipmentDetails.put("Country", order.getCountryShip());
        shipmentDetails.put("City", order.getCityShip());
        shipmentDetails.put("Zip", order.getZipShip());
        shipmentDetails.put("Street", order.getStreetShip());
        shipmentDetails.put("House number", String.valueOf(order.getHouseNumberShip()));
        return shipmentDetails;
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