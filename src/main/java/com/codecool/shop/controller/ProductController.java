package com.codecool.shop.controller;

import com.codecool.shop.config.DataBaseConfiguration;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.jdbc.ProductsCategoryDaoJdbc;
import com.codecool.shop.dao.jdbc.SupplierDaoJdbc;
import com.codecool.shop.model.Cart;
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
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {
    private final int notExistingValue = 0;
    private final String allCategories = "All";
    private Cart cart;
    private final DataSource dataSource = new DataBaseConfiguration().getDataSource();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        ProductsCategoryDaoJdbc productCategoryDataStore = new ProductsCategoryDaoJdbc(dataSource);
        SupplierDaoJdbc supplierDataStore = new SupplierDaoJdbc(dataSource);
        ProductService productService = new ProductService(dataSource);

        CartService cartService = new CartService(CartDaoMem.getInstance());

        System.out.println(req.getSession().getAttribute("cart-id"));
        if(req.getSession().getAttribute("cart-id") == null) {
            this.cart = cartService.createCart();
        }

        int categoryValueFromForm = selectedIdConverter(req.getParameter("category-select"));
        int supplierValueFromForm = selectedIdConverter(req.getParameter("supplier-select"));

        List<Product> products = preparingProductsListToShow(categoryValueFromForm, supplierValueFromForm, productService);


        context.setVariable("products", products);
        context.setVariable("cart", cart);


        context.setVariable("productsCategory", productService.getAllProducts());
//        context.setVariable("productsCategory", productCategoryDaoJdbc.getAll());

        context.setVariable("suppliers", supplierDataStore.getAll());
        context.setVariable("currentCategory", currentCategory(categoryValueFromForm, productCategoryDataStore));
        context.setVariable("currentSupplier", currentSupplier(supplierValueFromForm, supplierDataStore));
        context.setVariable("cartService", cartService);

        engine.process("product/index.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private List<Product> preparingProductsListToShow(int categoryId, int supplierId, ProductService productService) {
        if (categoryId != notExistingValue && supplierId != notExistingValue) {
            return productService.getProductsForCategoryAndSupplier(categoryId, supplierId);
        } else if (categoryId != notExistingValue) {
            return productService.getProductsForCategory(categoryId);
        } else if (supplierId != notExistingValue) {
            return productService.getProductsForSupplier(supplierId);
        } else {
            return productService.getAllProducts();
        }
    }

    private int selectedIdConverter(String valueFromSelect) {
        if (valueFromSelect != null) {
            if (!valueFromSelect.equals("")) {
                try {
                    int i = Integer.parseInt(valueFromSelect);
                    if (i >= notExistingValue) {
                        return i;
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Selected id does not exists");
                }
            }
        }return notExistingValue;
    }

    private String currentCategory(int categoryId, ProductCategoryDao productCategoryDataStore) {
        if (categoryId != notExistingValue) { return productCategoryDataStore.find(categoryId).getName(); }
        else { return allCategories; }
    }

    private String currentSupplier(int supplierId, SupplierDaoJdbc supplierDataStore){
        if (supplierId != notExistingValue) { return supplierDataStore.find(supplierId).getName(); }
        else { return allCategories; }
    }

}
