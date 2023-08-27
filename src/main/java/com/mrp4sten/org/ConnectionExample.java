package com.mrp4sten.org;

import java.sql.Date;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mrp4sten.org.model.Category;
import com.mrp4sten.org.model.Product;
import com.mrp4sten.org.service.CatalogueService;
import com.mrp4sten.org.service.Service;

public class ConnectionExample {
    public static void main(String[] args) throws SQLException {
        Logger logger = LoggerFactory.getLogger(ConnectionExample.class);

        Service service = new CatalogueService();

        logger.info("=== PRODUCT LIST ===");
        service.listProduct().forEach(p -> logger.info(p.toString()));

        logger.info("=== ADD NEW CATEGORY ===");
        Category category = new Category();
        category.setName("Pets");

        logger.info("=== ADD NEW PRODUCT ===");
        Product product = new Product();
        product.setName("Cats Food");
        product.setPrice(1000.00);
        product.setRecordDate(new Date(System.currentTimeMillis()));
        product.setSku("123qwerty");
        service.saveProductWithCategory(product, category);

        logger.info("=== PRODUCT ADDED SUCCESSFULLY {} ===", product.getId());
        service.listProduct().forEach(p -> logger.info(p.toString()));

    }
}