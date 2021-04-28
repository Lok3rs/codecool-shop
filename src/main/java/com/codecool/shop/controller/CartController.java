package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.CartService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.expression.Lists;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;


@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    private final CartService cartService =  new CartService(CartDaoMem.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        List<Product> uniqueProducts = getUniqueProducts();

        context.setVariable("cartService", cartService);
        context.setVariable("cart", uniqueProducts);
        engine.process("cart/cart.html", context, resp.getWriter());
    }

    private List<Product> getUniqueProducts(){
        Map<String, Integer> productsWithQuantity = new HashMap<>();
        List<Product> uniqueProducts = new LinkedList<>();
        for (Product product : cartService.getProductsInCart()) {
            int count = productsWithQuantity.getOrDefault(product.getName(), 0);
            productsWithQuantity.put(product.getName(), count + 1);
            if (productsWithQuantity.get(product.getName()) <= 1){
                uniqueProducts.add(product);
            }
        }
        return uniqueProducts;
    }
}
