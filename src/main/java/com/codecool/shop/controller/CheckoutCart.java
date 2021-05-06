package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.CartService;
import com.codecool.shop.service.OrderService;
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
    private final OrderService orderService = new OrderService(OrderDaoMem.getInstance());
    private int cartId;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        this.cartId = Integer.parseInt((String) req.getSession().getAttribute("cart-id"));

        Map<Product, Integer> productsMap = cartService.getProductsInCart(cartId);

        context.setVariable("cartService", cartService);
        context.setVariable("cartId", cartId);
        context.setVariable("orderService", orderService);
        context.setVariable("cart", productsMap);
        engine.process("cart/checkoutCart.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order orderFromCart = cartService.getOrderFromCart(cartId);
        orderFromCart.setOrderedProducts(cartService.getProductsInCart(cartId));
        cartService.setUserDetails(req, cartId);

        resp.sendRedirect("/order");
    }
}
