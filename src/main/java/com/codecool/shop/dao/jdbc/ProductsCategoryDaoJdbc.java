package com.codecool.shop.dao.jdbc;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsCategoryDaoJdbc implements ProductCategoryDao {
    private final DataSource dataSource;
    private final ProductsDepartamentDaoJdbc productsDepartamentDaoJdbc;

    public ProductsCategoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
        this.productsDepartamentDaoJdbc = new ProductsDepartamentDaoJdbc(dataSource);

    }



    @Override
    public void add(ProductCategory productCategory) {
    //    TODO if needed
        throw new RuntimeException("Method not implemented");
    }


        @Override
    public ProductCategory find(int id) {
        String sql = "SELECT name, department_id, description FROM products_category WHERE id = ?";
        try(Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                String name = resultSet.getString("name");
                String department = productsDepartamentDaoJdbc.find(resultSet.getInt("department_id"));
                String description = resultSet.getString("description");

                return new ProductCategory(name,department,description);
            }else { return null; }
        } catch (SQLException throwables) {
            throw new RuntimeException();
        }
    }

        @Override
    public void remove(int id) {
        String sql = "DELETE FROM products_category WHERE id = ?";
        try(Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

        @Override
    public List<ProductCategory> getAll() {
        String sql = "SELECT name, department_id, description FROM products_category";
        try(Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ProductCategory> productCategories = new ArrayList<>();

            while(resultSet.next()){
                String name = resultSet.getString("name");
                String department = productsDepartamentDaoJdbc.find(resultSet.getInt("department_id"));
                String description = resultSet.getString("description");
                productCategories.add(new ProductCategory(name,department,description));
            }
            return productCategories;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }
}
