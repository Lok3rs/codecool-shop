package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.AdminLogDaoMem;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.json.SerializationMenager;
import com.codecool.shop.mail.SendMail;
import com.codecool.shop.model.Action;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.AdminLogService;
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
    private final SerializationMenager serialization = new SerializationMenager();
    private final CartService cartService = new CartService(CartDaoMem.getInstance());
    private final AdminLogService adminLogService = new AdminLogService(AdminLogDaoMem.getInstance());
    private int cartId;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        this.cartId = Integer.parseInt((String) req.getSession().getAttribute("cart-id"));

        Order currentOrder = cartService.getOrderFromCart(cartId);
        Map<Product, Integer> orderedProducts = currentOrder.getOrderedProducts();
        orderService.addToOrderDaoMem(currentOrder);
        adminLogService.addLog(Action.ORDER_SUMMARY.getAction());

        orderService.setPaidOrderStatus(true, currentOrder.getId());
        summaryMail(currentOrder);
        serialization.saveLog(currentOrder);
        adminLogService.saveLogs(currentOrder.getId());

        context.setVariable("cartId", cartId);
        context.setVariable("paymentStatus", orderService.getPaidOrderStatus(currentOrder.getId()));
        context.setVariable("cartService", cartService);
        context.setVariable("orderedProducts", orderedProducts);

        req.getSession().setAttribute("cart-id", null);
        engine.process("order/order-summary.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        adminLogService.addLog(Action.CORRECT_PAYMENT.getAction());
        doGet(req, resp);
    }


    private void summaryMail(Order order){
        String userMail = order.getUserMail();
        String subject = "Order summary about order " + order.getId() + " from Codecool Shop by Radek & Dawid";
        String message = "We have successfully accepted your order. For more details about this order please visit our website.";

        try{
            SendMail.send(userMail,subject,message);
            System.out.println("Mail send successfully");
        }catch (Exception e){ System.out.println("System can't send this mail to user."); }
    }
}