package com.codecool.shop.config;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DataBaseConfiguration {

//    Edit configuration of project -> Runner -> Environment variables(before uncheck 'use project settings' option)
//    fill line bellow with your username and password and copy to right place
//    password=pass;dbName=codecool_shop;user=user

    public DataSource getDataSource(){
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        String dbName = System.getenv("dbName");
        String user = System.getenv("user");
        String password = System.getenv("password");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        try {
            dataSource.getConnection().close();
            System.out.println("Connection ok.");
        } catch (SQLException throwables) {
            throw new RuntimeException("connection establishment error, check your environment variables");
        }

        return dataSource;
    }
}
