package com.mrp4sten.org;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import com.mrp4sten.org.model.Category;
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

                System.out.println("=== ADD PRODUCT ===");
                java.util.Date date = new java.util.Date();
                Category category = new Category();
                category.setId(1L);
                Product product = new Product(null, "Samsung s23 PRO", 16000.00, new Date(date.getTime()), category);
                repository.save(product);
                repository.list().forEach(System.out::println);

                System.out.println("=== EDIT PRODUCT ===");
                Product updateProduct = new Product(4L, "Samsung Galaxy s23 PRO", 16000.00,
                        null, category);
                repository.save(updateProduct);
                repository.list().forEach(System.out::println);

                System.out.println("=== REMOVE PRODUCT ===");
                repository.remove(4L);
                repository.list().forEach(System.out::println);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}