//package com.codecool.shop.controller;
//
//import com.codecool.shop.config.TemplateEngineUtil;
//import com.codecool.shop.dao.implementation.CartDaoMem;
//import com.codecool.shop.dao.implementation.OrderDaoMem;
//import com.codecool.shop.model.Product;
//import com.codecool.shop.service.CartService;
//import com.codecool.shop.service.OrderService;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.WebContext;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Map;
//
//
//@WebServlet(urlPatterns = {"/order"})
//public class OrderController extends HttpServlet {
//
//    private OrderService  orderService = new OrderService(OrderDaoMem.getInstance());
//    private CartService cartService = new CartService(CartDaoMem.getInstance());
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
//        WebContext context = new WebContext(req, resp, req.getServletContext());
//        Map<Product, Integer> orderedProducts = orderService.getProductsInCart();
//
//        context.setVariable("orderService", orderService);
//        context.setVariable("orderedProducts", orderedProducts);
//
//        engine.process("order/confirm.html", context, resp.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
//        WebContext context = new WebContext(req, resp, req.getServletContext());
//
//        String paymentMethod = req.getParameter("payment-options");
//
//        context.setVariable("totalPrice", orderService.getAllOrderPrice());
//
//        if(paymentMethod.equals("Credit card")){
//            engine.process("payment/creditCard.html", context, resp.getWriter());
//        }else{
//            engine.process("payment/paypal.html", context, resp.getWriter());
//        }
//    }
//}
