package com.mrp4sten.org;

import java.sql.Connection;
import java.sql.SQLException;

import com.mrp4sten.org.model.Product;
import com.mrp4sten.org.repository.ProductRepositoryImpl;
import com.mrp4sten.org.repository.Repository;
import com.mrp4sten.org.util.DBConnection;

public class ConnectionExample {
    public static void main(String[] args) {
        try {
            try (Connection connection = DBConnection.getInstance()) {
                Repository<Product> repository = new ProductRepositoryImpl();
                repository.list().forEach(p -> System.out.println(p.getName()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}