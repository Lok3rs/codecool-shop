package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.CartService;
import com.codecool.shop.service.ProductService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;



@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    private final CartService cartService =  new CartService(CartDaoMem.getInstance());
    private final ProductService productService = new ProductService(ProductDaoMem.getInstance(), ProductCategoryDaoMem.getInstance(), SupplierDaoMem.getInstance());
    private final List<Product> products = productService.getAllProducts();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        List<Product> uniqueProducts = getUniqueProducts();

        context.setVariable("cartService", cartService);
        context.setVariable("cart", uniqueProducts);
        engine.process("cart/cart.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("increase") != null ? "increase" : "decrease";
        for (Product product : products) {
            if (req.getParameter(product.getName()) != null){
                switch (action){
                    case "increase" -> cartService.addToCart(product);
                    case "decrease" -> cartService.removeFromCart(product);
                }
            }
        }
        resp.sendRedirect("/cart");
    }

    private List<Product> getUniqueProducts(){
        Map<String, Integer> productsWithQuantity = new HashMap<>();
        List<Product> uniqueProducts = new LinkedList<>();
        for (Product product : cartService.getProductsInCart()) {
            int count = productsWithQuantity.getOrDefault(product.getName(), 0);
            productsWithQuantity.put(product.getName(), count + 1);
            if (productsWithQuantity.get(product.getName()) == 1){
                uniqueProducts.add(product);
            }
        }
        return uniqueProducts;
    }
}
