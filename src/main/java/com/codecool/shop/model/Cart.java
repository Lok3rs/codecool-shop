package com.codecool.shop.model;

import java.util.LinkedList;
import java.util.List;

public class Cart {

    List<Product> productsInCart;

    public Cart(){
        this.productsInCart = new LinkedList<>();
    }

    public List<Product> getProductsInCart(){
        return this.productsInCart;
    }

    public void addProductToCart(Product product){
        this.productsInCart.add(product);
    }

    public void removeProductFromCart(Product product){
        this.productsInCart.remove(product);
    }

    public void clearCart(){
        this.productsInCart.clear();
    }
}
