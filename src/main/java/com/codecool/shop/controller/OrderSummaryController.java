package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.json.SerializationMenager;
import com.codecool.shop.mail.SendMail;
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


@WebServlet(urlPatterns = {"/order-summary"})
public class OrderSummaryController extends HttpServlet {

    private final OrderService orderService = new OrderService(OrderDaoMem.getInstance());
    private final SerializationMenager serialization = new SerializationMenager();
    private final CartService cartService = new CartService(CartDaoMem.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        Order currentOrder = orderService.getOrder();

        Map<Product, Integer> orderedProducts = orderService.getProductsInCart();
        orderService.setPaidOrderStatus(true);
        summaryMail(currentOrder);
        serialization.saveLog(currentOrder);
        cartService.clearCart();

        context.setVariable("paymentStatus", orderService.getPaidOrderStatus());
        context.setVariable("orderService", orderService);
        context.setVariable("orderedProducts", orderedProducts);

        engine.process("order/order-summary.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


//    ważniejsza była dla mnie funkcjonalność niż wiadomość więc nie zawiera ona szczególnie ciekawych informacji,
//    można tu coś ciekawszego napisać
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