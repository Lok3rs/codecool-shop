package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;

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

    public void addToCart(Product product, int id) {
        getCart(id).addProductToCart(product);
    }

    public void removeFromCart(Product product, int id) {
        getCart(id).removeProductFromCart(product);
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
}
