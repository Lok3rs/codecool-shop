package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.service.CartService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet (urlPatterns = {"/clear_cart"})
public class ClearCartController extends HttpServlet {

    private int cartId;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartService cartService = new CartService(CartDaoMem.getInstance());
        this.cartId = Integer.parseInt((String) req.getSession().getAttribute("cart-id"));

        cartService.clearCart(cartId);
        resp.sendRedirect("/cart");
    }
}

