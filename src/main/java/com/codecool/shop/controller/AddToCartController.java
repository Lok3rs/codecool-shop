package com.codecool.shop.controller;

import com.codecool.shop.config.DataBaseConfiguration;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.CartService;
import com.codecool.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/add_to_cart"})
public class AddToCartController extends HttpServlet {
    private final DataSource dataSource = new DataBaseConfiguration().getDataSource();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ProductService productService = new ProductService(ProductDaoMem.getInstance(), ProductCategoryDaoMem.getInstance(), SupplierDaoMem.getInstance());
        ProductService productService = new ProductService(dataSource);
        CartService cartService = new CartService(CartDaoMem.getInstance());
        List<Product> products = productService.getAllProducts();

        String cartId = req.getParameter("cart-id");

        for (Product product : products) {
            if(req.getParameter(product.getName()) != null){
                cartService.addToCart(product, Integer.parseInt(cartId));
            }
        }

        req.getSession().setAttribute("cart-id", cartId);
        resp.sendRedirect("/cart");
    }
}
