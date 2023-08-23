package com.mrp4sten.org;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mrp4sten.org.model.Category;
import com.mrp4sten.org.model.Product;
import com.mrp4sten.org.repository.ProductRepositoryImpl;
import com.mrp4sten.org.repository.Repository;
import com.mrp4sten.org.util.DBConnection;

public class ConnectionExample {
    public static void main(String[] args) throws SQLException {
        Logger logger = LoggerFactory.getLogger(ConnectionExample.class);
        try (Connection connection = DBConnection.getConnection()) {

            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }

            try {

                logger.info("=== PRODUCT LIST ===");
                Repository<Product> repository = new ProductRepositoryImpl(connection);
                repository.list().forEach(p -> logger.info(p.toString()));

                logger.info("=== PRODUCT WITH ID 2 ===");
                logger.info("Product: {}", repository.byId(2L));

                logger.info("=== ADD PRODUCT ===");
                java.util.Date date = new java.util.Date();
                Category category = new Category();
                category.setId(1L);
                Product product = new Product(null, "Samsung s23 PRO", 16000.00, new Date(date.getTime()), category,
                        "abc5678def");
                repository.save(product);
                repository.list().forEach(p -> logger.info(p.toString()));

                logger.info("=== EDIT PRODUCT ===");
                Product updateProduct = new Product(4L, "Samsung Galaxy s23 PRO", 16000.00,
                        null, category, "abc1234def");
                repository.save(updateProduct);
                repository.list().forEach(p -> logger.info(p.toString()));

                logger.info("=== REMOVE PRODUCT ===");
                repository.remove(4L);
                repository.list().forEach(p -> logger.info(p.toString()));

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();

            }

        }

    }
}