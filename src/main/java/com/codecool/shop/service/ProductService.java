package com.codecool.shop.service;

import com.codecool.shop.dao.jdbc.ProductsCategoryDaoJdbc;
import com.codecool.shop.dao.jdbc.ProductsDaoJdbc;
import com.codecool.shop.dao.jdbc.SupplierDaoJdbc;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.util.List;

public class ProductService{
    private final ProductsDaoJdbc productDaoJdbc;
    private final ProductsCategoryDaoJdbc productCategoryDaoJdbc;
    private final SupplierDaoJdbc supplierDaoJdbc;

    public ProductService(DataSource dataSource) {
        this.productDaoJdbc = new ProductsDaoJdbc(dataSource);
        this.productCategoryDaoJdbc = new ProductsCategoryDaoJdbc(dataSource);
        this.supplierDaoJdbc = new SupplierDaoJdbc(dataSource);
    }

    public ProductCategory getProductCategory(int categoryId){
        return productCategoryDaoJdbc.find(categoryId);
    }

    public List<Product> getProductsForCategory(int categoryId){
        var category = productCategoryDaoJdbc.find(categoryId);
        return productDaoJdbc.getBy(category);
    }

    public List<Product> getProductsForSupplier(int supplierId){
        var supplier = supplierDaoJdbc.find(supplierId);
        return productDaoJdbc.getBy(supplier);
    }

    public List<Product> getProductsForCategoryAndSupplier(int categoryId, int supplierId){
        var category = productCategoryDaoJdbc.find(categoryId);
        var supplier = supplierDaoJdbc.find(supplierId);
        return productDaoJdbc.getBy(category, supplier);
    }

    public List<Product> getAllProducts(){
        return productDaoJdbc.getAll();
    }

}
