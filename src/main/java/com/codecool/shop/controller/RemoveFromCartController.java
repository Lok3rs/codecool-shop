package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.CartService;
import com.codecool.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (urlPatterns = {"/remove_from_cart"})
public class RemoveFromCartController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = new ProductService(ProductDaoMem.getInstance(), ProductCategoryDaoMem.getInstance(), SupplierDaoMem.getInstance());
        CartService cartService = new CartService(CartDaoMem.getInstance());
        List<Product> products = productService.getAllProducts();
        int cartId = Integer.parseInt((String) req.getSession().getAttribute("cart-id"));

        for (Product product : products) {
            if (req.getParameter(product.getName()) != null) {
                cartService.removeAllTheSameProducts(product, cartId);
            }
        }

        resp.sendRedirect("/cart");
    }
}
