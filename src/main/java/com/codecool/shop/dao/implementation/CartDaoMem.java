package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartDaoMem implements CartDao {

    private static CartDaoMem instance;
    private List<Cart> data;

    private CartDaoMem(){
        this.data = new ArrayList<>();
    }

    public static CartDaoMem getInstance(){
        if (instance == null){
            instance = new CartDaoMem();
        }
        return instance;
    }


    @Override
    public void add(Cart cart) {
        cart.setId(data.size() + 1);
        data.add(cart);
    }


    @Override
    public Cart find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }


    @Override
    public void remove(int id) {
        data.remove(find(id));
    }


    @Override
    public List<Cart> getAll() {
        return data;
    }
}
