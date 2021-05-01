package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.CartService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@WebServlet(urlPatterns = {"/checkout-cart"})
public class CheckoutCart extends HttpServlet {

    private final CartService cartService =  new CartService(CartDaoMem.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        Map<Product, Integer> productsMap = cartService.getProductsInCart();

        context.setVariable("cartService", cartService);
        context.setVariable("cart", productsMap);

        engine.process("cart/checkoutCart.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("dupa");
    }
}
