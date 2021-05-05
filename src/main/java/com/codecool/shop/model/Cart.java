package com.codecool.shop.model;

import java.util.Map;

public class Cart extends BaseModel{

    private Order order;

    public Cart(){
        this.order = new Order();
    }

    public Map<Product, Integer> getProductsInCart(){
        return order.getOrderedProducts();
    }

    public void addProductToCart(Product product){
        order.addProduct(product);
    }

    public void removeProductFromCart(Product product){
        order.removeProduct(product);
    }

    public void removeAllTheSameProducts(Product product){
        order.removeAllTheSameProducts(product);
    }


    public void clearCart(){
        order.clearOrder();
    }
}
