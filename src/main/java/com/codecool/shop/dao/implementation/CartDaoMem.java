package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;

import java.util.List;

public class CartDaoMem implements CartDao {

    private static CartDaoMem instance = null;
    private static Cart cart = new Cart();

    // Private Constructor prevents any other class from instantiating
    private CartDaoMem(){}

    public static CartDaoMem getInstance(){
        if (instance == null){
            instance = new CartDaoMem();
        }
        return instance;
    }

    @Override
    public void addToCart(Product product) {
        cart.addProductToCart(product);
    }

    @Override
    public void removeFromCart(Product product) {
        cart.removeProductFromCart(product);
    }

    @Override
    public void clearCart() {
        cart.clearCart();
    }

    @Override
    public List<Product> getProductsInCart() {
        return cart.getProductsInCart();
    }
}
