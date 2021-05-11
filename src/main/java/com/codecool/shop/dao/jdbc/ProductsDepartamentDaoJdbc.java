package com.codecool.shop.dao.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsDepartamentDaoJdbc {

    private final DataSource dataSource;

    public ProductsDepartamentDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void add(String department) {
        String sql = "INSERT INTO products_department(name) VALUES(?)";
        try(Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,department);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public String find(int id) {
        String sql = "SELECT name FROM products_department WHERE id = ?";
        try(Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return resultSet.getString(1);
            }else { return null; }
        } catch (SQLException throwables) {
            throw new RuntimeException();
        }
    }


    public void remove(int id) {
        String sql = "DELETE FROM products_department WHERE id = ?";
        try(Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public List<String> getAll() {
        String sql = "SELECT name FROM products_department";
        try(Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> departments = new ArrayList<>();

            while(resultSet.next()){
                String department = resultSet.getString("name");
                departments.add(department);
            }return departments;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }
}
