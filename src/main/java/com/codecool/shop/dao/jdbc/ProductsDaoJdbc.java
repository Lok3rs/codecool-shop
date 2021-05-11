package com.codecool.shop.dao.jdbc;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsDaoJdbc implements ProductDao {
    private final DataSource dataSource;
    private final ProductsCategoryDaoJdbc productsCategoryDaoJdbc;
    private final SupplierDaoJdbc supplierDaoJdbc;

    public ProductsDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
        this.productsCategoryDaoJdbc = new ProductsCategoryDaoJdbc(dataSource);
        this.supplierDaoJdbc = new SupplierDaoJdbc(dataSource);
    }


    @Override
    public void add(Product product) {
        //    TODO if needed
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public Product find(int id) {
        String sql = "SELECT name, description, currency, category_id, supplier_id, price FROM products WHERE id = ?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String currency = resultSet.getString("currency");
                ProductCategory category = productsCategoryDaoJdbc.find(resultSet.getInt("category_id"));
                Supplier supplier = supplierDaoJdbc.find(resultSet.getInt("supplier_id"));
                float price = resultSet.getFloat("price");
                Product product = new Product(name, price, currency, description, category, supplier);
                product.setId(id);
                return product;
            } else {
                return null;
            }
        } catch (SQLException throwables) {
            throw new RuntimeException();
        }
    }

    @Override
    public void remove(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        String sql = "SELECT * FROM products";
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String currency = resultSet.getString("currency");
                ProductCategory category = productsCategoryDaoJdbc.find(resultSet.getInt("category_id"));
                Supplier supplier = supplierDaoJdbc.find(resultSet.getInt("supplier_id"));
                float price = resultSet.getFloat("price");
                Product product = new Product(name, price, currency, description, category, supplier);
                product.setId(resultSet.getInt("id"));
                products.add(product);
            }
            return products;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();

        }
    }

//    Tak wiem ze nie powinno być, tylko testy
    @Override
    public List<Product> getBy(Supplier supplier) {
        List<Product> allProducts = getAll();
        return allProducts.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        List<Product> allProducts = getAll();
        return allProducts.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory, Supplier supplier) {
        List<Product> allProducts = getAll();
        return allProducts.stream().filter(t -> t.getProductCategory().equals(productCategory) && t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }
}
