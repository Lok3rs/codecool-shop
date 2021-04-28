package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;

import java.util.List;

public class CartService {

    private CartDao cartDao;

    public CartService(CartDao cartDao){
        this.cartDao = cartDao;
    }

    public void addToCart(Product product){
        System.out.println("Added");
        cartDao.addToCart(product);
    }

    public void removeFromCart(Product product){
        cartDao.removeFromCart(product);
    }

    public void clearCart(){
        cartDao.clearCart();
    }

    public List<Product> getProductsInCart(){
        return cartDao.getProductsInCart();
    }

    public int countProduct(Product countedProduct){
        int count = 0;
        for (Product product : getProductsInCart()) {
            count += product.getName().equals(countedProduct.getName()) ? 1 : 0;
        }
        return count;
    }

    public float getAllProductsPrice(Product product){
        return countProduct(product) * product.getDefaultPrice();
    }
}
