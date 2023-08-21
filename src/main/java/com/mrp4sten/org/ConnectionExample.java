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

                System.out.println("=== PRODUCT LIST ===");
                Repository<Product> repository = new ProductRepositoryImpl();
                repository.list().forEach(System.out::println);

                System.out.println("=== PRODUCT WITH ID 2 ===");
                System.out.println(repository.byId(2L));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}