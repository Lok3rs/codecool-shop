package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

public class CartService {

    private CartDao cartDao;

    public CartService(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public Cart getCart(int id){
        return cartDao.find(id);
    }

    public Cart createCart(){
        Cart cart = new Cart();
        cartDao.add(cart);
        return cart;
    }

    public Order getOrderFromCart(int id){
        return getCart(id).getOrder();
    }

    public void addToCart(Product product, int id) {
        getCart(id).addProductToCart(product);
    }

    public void removeFromCart(Product product, int id) {
        getCart(id).removeProductFromCart(product);
    }

    public void removeAllTheSameProducts(Product product, int id){
        getCart(id).removeAllTheSameProducts(product);
    }

    public void clearCart(int id) {
        getCart(id).clearCart();
    }

    public Map<Product, Integer> getProductsInCart(int id) {
        return getCart(id).getProductsInCart();
    }

    public int countProduct(Product countedProduct, int id) {
        for (Map.Entry<Product, Integer> productEntry : getProductsInCart(id).entrySet()) {
            if (countedProduct.getName().equals(productEntry.getKey().getName())) return productEntry.getValue();
        }
        return 0;
    }

    public float getAllProductsPrice(Product product, int id) {
        return countProduct(product, id) * product.getDefaultPrice();
    }


    public float getAllOrderPrice(int id) {
        float price = 0;
        for (Map.Entry<Product, Integer> productEntry : getOrderFromCart(id).getOrderedProducts().entrySet()) {
            price += getAllProductsPrice(productEntry.getKey(), id);
        }
        return price;
    }

    public Map<String, String> getBillingDetails(int id) {
        Order order  = getOrderFromCart(id);
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

    public void setUserDetails(HttpServletRequest req, int id) {
        Order order  = getOrderFromCart(id);
        order.setUserFirstName(req.getParameter("first-name"));
        order.setUserLastName(req.getParameter("last-name"));
        order.setUserMail(req.getParameter("email"));
        order.setUserPhone(Integer.parseInt(req.getParameter("phone")));
        order.setCountryBilling(req.getParameter("country"));
        order.setCityBilling(req.getParameter("city"));
        order.setZipBilling(req.getParameter("zip"));
        order.setStreetBilling(req.getParameter("street"));
        order.setHouseNumberBilling(Integer.parseInt(req.getParameter("house-number")));

        order.setCountryShip(req.getParameter("country-ship"));
        order.setCityShip(req.getParameter("city-ship"));
        order.setStreetShip(req.getParameter("city-ship"));
        order.setZipShip(req.getParameter("zip-ship"));
        order.setHouseNumberShip(Integer.parseInt(req.getParameter("house-number-ship")));

    }

    public Map<String, String> getShipmentDetails(int id) {
        Order order = getOrderFromCart(id);

        Map<String, String> shipmentDetails = new LinkedHashMap<>();
        shipmentDetails.put("Country", order.getCountryShip());
        shipmentDetails.put("City", order.getCityShip());
        shipmentDetails.put("Zip", order.getZipShip());
        shipmentDetails.put("Street", order.getStreetShip());
        shipmentDetails.put("House number", String.valueOf(order.getHouseNumberShip()));
        return shipmentDetails;
    }
}
