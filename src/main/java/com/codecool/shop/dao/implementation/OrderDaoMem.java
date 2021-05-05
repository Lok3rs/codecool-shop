package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoMem implements OrderDao {

    private static OrderDaoMem instance;
    private final List<Order> data;

    private OrderDaoMem(){
        this.data = new ArrayList<>();
    }

    public static OrderDaoMem getInstance(){
        if (instance == null){
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

//    @Override
//    public void setUserDetails(HttpServletRequest req) {
//        setUserFirstName(req.getParameter("first-name"));
//        setUserLastName(req.getParameter("last-name"));
//        setUserEmail(req.getParameter("email"));
//        setUserPhone(Integer.parseInt(req.getParameter("phone")));
//        setCountryBilling(req.getParameter("country"));
//        setCityBilling(req.getParameter("city"));
//        setZipBilling(req.getParameter("zip"));
//        setStreetBilling(req.getParameter("street"));
//        setHouseNumberBilling(Integer.parseInt(req.getParameter("house-number")));
//
//        setCountryShip(req.getParameter("country-ship"));
//        setCityShip(req.getParameter("city-ship"));
//        setStreetShip(req.getParameter("city-ship"));
//        setZipShip(req.getParameter("zip-ship"));
//        setHouseNumberShip(Integer.parseInt(req.getParameter("house-number-ship")));
//    }
//
//    @Override
//    public Map<String, String> getBillingDetails() {
//        Map<String, String> billingDetails = new LinkedHashMap<>();
//        billingDetails.put("First name", order.getUserFirstName());
//        billingDetails.put("Last name", order.getUserLastName());
//        billingDetails.put("Email", order.getUserMail());
//        billingDetails.put("Phone", String.valueOf(order.getUserPhone()));
//        billingDetails.put("Country", order.getCountryBilling());
//        billingDetails.put("City", order.getCityBilling());
//        billingDetails.put("Zip", order.getZipBilling());
//        billingDetails.put("Street", order.getStreetBilling());
//        billingDetails.put("House number", String.valueOf(order.getHouseNumberBilling()));
//        return billingDetails;
//    }
//
//    @Override
//    public Map<String, String> getShipmentDetails() {
//        Map<String, String> shipmentDetails = new LinkedHashMap<>();
//        shipmentDetails.put("Country", order.getCountryShip());
//        shipmentDetails.put("City", order.getCityShip());
//        shipmentDetails.put("Zip", order.getZipShip());
//        shipmentDetails.put("Street", order.getStreetShip());
//        shipmentDetails.put("House number", String.valueOf(order.getHouseNumberShip()));
//        return shipmentDetails;
//    }
//
}