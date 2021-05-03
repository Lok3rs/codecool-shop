package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.json.SerializationMenager;
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


@WebServlet(urlPatterns = {"/order-summary"})
public class OrderSummaryController extends HttpServlet {

    private final OrderService orderService = new OrderService(OrderDaoMem.getInstance());
    private final CartService cartService = new CartService(CartDaoMem.getInstance());
    private final SerializationMenager serialization = new SerializationMenager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        Map<Product, Integer> orderedProducts = orderService.getProductsInCart();
        orderService.setPaidOrderStatus(true);
        serialization.saveLog(orderService.getOrder());

        context.setVariable("paymentStatus", orderService.getPaidOrderStatus());
        context.setVariable("orderService", orderService);
        context.setVariable("orderedProducts", orderedProducts);

        engine.process("order/order-summary.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}