package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDaoMem supplierDataStore = SupplierDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore, productCategoryDataStore, supplierDataStore);

        String categoryValueFromForm = req.getParameter("category-select");
        String supplierValueFromForm = req.getParameter("supplier-select");

        List<Product> products = preparingProductsListToShow(categoryValueFromForm, supplierValueFromForm, productService);

        context.setVariable("products", products);
        context.setVariable("productsCategory", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());
        context.setVariable("currentCategory", currentCategory(categoryValueFromForm, productCategoryDataStore));
        context.setVariable("currentSupplier", currentSupplier(supplierValueFromForm, supplierDataStore));

        engine.process("product/index.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


    private List<Product> preparingProductsListToShow(String categoryValue, String supplierValue, ProductService productService) {
        int notExistingValue = 0;
        int categoryId = selectedIdConverter(categoryValue);
        int supplierId = selectedIdConverter(supplierValue);

        if (categoryId != notExistingValue && supplierId != notExistingValue) {
            return productService.getProductsForCategoryAndSupplier(categoryId, supplierId);
        } else if (categoryId != notExistingValue && supplierId == notExistingValue) {
            return productService.getProductsForCategory(categoryId);
        } else if (categoryId == notExistingValue && supplierId != notExistingValue) {
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
                    if (i >= 0) {
                        return i;
                    }
                } catch (Exception e) {
                    new Exception("Selected id does not exists");
                }
            }
        }
        return 0;
    }

    private String currentCategory(String category, ProductCategoryDao productCategoryDataStore) {
        int categoryId = selectedIdConverter(category);

        if (categoryId != 0) {
            return productCategoryDataStore.find(categoryId).getName();
        } else {
            return "All";
        }
    }

    private String currentSupplier(String supplier, SupplierDaoMem supplierDataStore){
        int supplierId = selectedIdConverter(supplier);

        if (supplierId != 0) {
            return supplierDataStore.find(supplierId).getName();
        } else {
            return "All";
        }
    }

}
