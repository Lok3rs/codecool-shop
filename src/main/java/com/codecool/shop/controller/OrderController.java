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


@WebServlet(urlPatterns = {"/order"})
public class OrderController extends HttpServlet {

    private final CartService cartService = new CartService(CartDaoMem.getInstance());
    private int cartId;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        this.cartId = Integer.parseInt((String) req.getSession().getAttribute("cart-id"));

        Map<Product, Integer> orderedProducts = cartService.getProductsInCart(cartId);
        System.out.println(cartService.getAllOrderPrice(cartId));

        context.setVariable("cartService", cartService);
        context.setVariable("cartId", cartId);
        context.setVariable("orderedProducts", orderedProducts);
        engine.process("order/confirm.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String paymentMethod = req.getParameter("payment-options");

        context.setVariable("totalPrice", cartService.getAllOrderPrice(cartId));

        if(paymentMethod.equals("Credit card")){
            engine.process("payment/creditCard.html", context, resp.getWriter());
        }else{
            engine.process("payment/paypal.html", context, resp.getWriter());
        }
    }
}
