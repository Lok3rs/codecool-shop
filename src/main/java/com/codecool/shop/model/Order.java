package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.Currency;
import java.util.LinkedHashMap;
import java.util.Map;

public class Order extends BaseModel {

    private ArrayList<Product> productsList;
    private String userFirstName;
    private String userLastName;
    private String userMail;
    private int userPhone;

    private String countryBilling;
    private String cityBilling;
    private String zipBilling;
    private String streetBilling;
    private int houseNumberBilling;

    private String countryShip;
    private String cityShip;
    private String zipShip;
    private String streetShip;
    private int houseNumberShip;


    public Order() {
        super();
        this.productsList = new ArrayList<>();
    }

    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(ArrayList<Product> productsList) {
        this.productsList = productsList;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public int getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(int userPhone) {
        this.userPhone = userPhone;
    }

    public String getCountryBilling() {
        return countryBilling;
    }

    public void setCountryBilling(String countryBilling) {
        this.countryBilling = countryBilling;
    }

    public String getCityBilling() {
        return cityBilling;
    }

    public void setCityBilling(String cityBilling) {
        this.cityBilling = cityBilling;
    }

    public String getZipBilling() {
        return zipBilling;
    }

    public void setZipBilling(String zipBilling) {
        this.zipBilling = zipBilling;
    }

    public String getStreetBilling() {
        return streetBilling;
    }

    public void setStreetBilling(String streetBilling) {
        this.streetBilling = streetBilling;
    }

    public int getHouseNumberBilling() {
        return houseNumberBilling;
    }

    public void setHouseNumberBilling(int houseNumberBilling) {
        this.houseNumberBilling = houseNumberBilling;
    }

    public String getCountryShip() {
        return countryShip;
    }

    public void setCountryShip(String countryShip) {
        this.countryShip = countryShip;
    }

    public String getCityShip() {
        return cityShip;
    }

    public void setCityShip(String cityShip) {
        this.cityShip = cityShip;
    }

    public String getZipShip() {
        return zipShip;
    }

    public void setZipShip(String zipShip) {
        this.zipShip = zipShip;
    }

    public String getStreetShip() {
        return streetShip;
    }

    public void setStreetShip(String streetShip) {
        this.streetShip = streetShip;
    }

    public int getHouseNumberShip() {
        return houseNumberShip;
    }

    public void setHouseNumberShip(int houseNumberShip) {
        this.houseNumberShip = houseNumberShip;
    }


}


//    private Map<Product, Integer> orderedProducts;
//
//    public Order(){
//        super("");
//        this.orderedProducts = new LinkedHashMap<>();
//    }
//
//    public void addProduct(Product product){
//        for (Map.Entry<Product, Integer> productEntry : orderedProducts.entrySet()) {
//            if (product.getName().equals(productEntry.getKey().getName())){
//                orderedProducts.put(productEntry.getKey(), productEntry.getValue() + 1);
//                return;
//            }
//        }
//        orderedProducts.put(product, 1);
//    }
//
//    public void removeProduct(Product product){
//        for (Map.Entry<Product, Integer> productEntry : orderedProducts.entrySet()) {
//            if (product.getName().equals(productEntry.getKey().getName())){
//                if (productEntry.getValue() > 1) {
//                    orderedProducts.put(productEntry.getKey(), productEntry.getValue() - 1);
//                } else {
//                    orderedProducts.remove(product);
//                }
//            }
//        }
//    }
//
//    public void clearOrder(){
//        orderedProducts.clear();
//    }
//
//    public Map<Product, Integer> getOrderedProducts(){
//        return this.orderedProducts;
//    }

//    @Override
//    public String toString() {
//        return String.format("id: %1$d, " +
//                        "name: %2$s, " +
//                        "defaultPrice: %3$f, " +
//                        "defaultCurrency: %4$s, " +
//                        "productCategory: %5$s, " +
//                        "supplier: %6$s",
//                this.id,
//                this.name,
//                this.defaultPrice,
//                this.defaultCurrency.toString(),
//                this.productCategory.getName(),
//                this.supplier.getName());
//    }