package com.codecool.shop.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Order {

    private Map<Product, Integer> orderedProducts;

    public Order(){
        this.orderedProducts = new LinkedHashMap<>();
    }

    public void addProduct(Product product){
        for (Map.Entry<Product, Integer> productEntry : orderedProducts.entrySet()) {
            if (product.getName().equals(productEntry.getKey().getName())){
                orderedProducts.put(productEntry.getKey(), productEntry.getValue() + 1);
                return;
            }
        }
        orderedProducts.put(product, 1);
    }

    public void removeProduct(Product product){
        for (Map.Entry<Product, Integer> productEntry : orderedProducts.entrySet()) {
            if (product.getName().equals(productEntry.getKey().getName())){
                if (productEntry.getValue() > 1) {
                    orderedProducts.put(productEntry.getKey(), productEntry.getValue() - 1);
                } else {
                    orderedProducts.remove(product);
                }
            }
        }
    }

    public void clearOrder(){
        orderedProducts.clear();
    }

    public Map<Product, Integer> getOrderedProducts(){
        return this.orderedProducts;
    }
}
