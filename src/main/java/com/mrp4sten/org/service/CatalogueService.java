package com.mrp4sten.org.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.mrp4sten.org.model.Category;
import com.mrp4sten.org.model.Product;
import com.mrp4sten.org.repository.CategoryRepositoryImpl;
import com.mrp4sten.org.repository.ProductRepositoryImpl;
import com.mrp4sten.org.repository.Repository;
import com.mrp4sten.org.util.DBConnection;

public class CatalogueService implements Service {

  private Repository<Product> productRepository;
  private Repository<Category> categoryRepository;

  public CatalogueService() {
    this.productRepository = new ProductRepositoryImpl();
    this.categoryRepository = new CategoryRepositoryImpl();

  }

  @Override
  public List<Product> listProduct() throws SQLException {
    try (Connection connection = DBConnection.getConnection()) {
      productRepository.setConnection(connection);
    }

    return Collections.emptyList();
  }

  @Override
  public List<Category> listCategory() throws SQLException {
    try (Connection connection = DBConnection.getConnection()) {
      categoryRepository.setConnection(connection);
    }

    return Collections.emptyList();
  }

  @Override
  public Product productById(Long id) throws SQLException {
    try (Connection connection = DBConnection.getConnection()) {
      productRepository.setConnection(connection);
    }

    return null;
  }

  @Override
  public Category categoryById(Long id) throws SQLException {
    try (Connection connection = DBConnection.getConnection()) {
      categoryRepository.setConnection(connection);
    }

    return null;
  }

  @Override
  public Product saveProduct(Product product) throws SQLException {
    try (Connection connection = DBConnection.getConnection()) {
      productRepository.setConnection(connection);
    }

    return null;
  }

  @Override
  public Category saveCategory(Category category) throws SQLException {
    try (Connection connection = DBConnection.getConnection()) {
      categoryRepository.setConnection(connection);
    }

    return null;
  }

  @Override
  public void deleteProduct(Long id) throws SQLException {
    try (Connection connection = DBConnection.getConnection()) {
      productRepository.setConnection(connection);
    }

  }

  @Override
  public void deleteCategory(Long id) throws SQLException {
    try (Connection connection = DBConnection.getConnection()) {
      categoryRepository.setConnection(connection);
    }
  }

  @Override
  public void saveProductWithCategory(Product product, Category category) throws SQLException {
    try (Connection connection = DBConnection.getConnection()) {
      productRepository.setConnection(connection);
      categoryRepository.setConnection(connection);
    }
  }

}
