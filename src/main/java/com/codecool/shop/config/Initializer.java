package com.codecool.shop.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        ProductDao productDataStore = ProductDaoMem.getInstance();
//        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
//        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
            DataSource dataSource = new DataBaseConfiguration().getDataSource();
//            ProductsCategoryDaoJdbc productCategoryDaoJdbc = new ProductsCategoryDaoJdbc(dataSource);

//        //setting up a new supplier
//        Supplier amazon = new Supplier("Amazon", "Digital content and services");
//        supplierDataStore.add(amazon);
//        Supplier lenovo = new Supplier("Lenovo", "Computers");
//        supplierDataStore.add(lenovo);
//        Supplier nokia  = new Supplier("Nokia", "Mobile phones");
//        supplierDataStore.add(nokia);
//
//        //setting up a new product category
//        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
//        productCategoryDataStore.add(tablet);
//        ProductCategory phone = new ProductCategory("Mobile phone", "Hardware", "A portable telephone that can make and receive calls over a radio frequency link while the user is moving within a telephone service area." );
//        productCategoryDataStore.add(phone);
//
//        productCategoryDaoJdbc.add(tablet);
//
//
//        //setting up products and printing it
//        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
//        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
//        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
//        productDataStore.add(new Product("Nokia 3310", 500, "USD", "Best mobile phone ever", phone, nokia));
    }

}
