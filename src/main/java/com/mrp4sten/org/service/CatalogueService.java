package com.mrp4sten.org.service;

import java.sql.Connection;
import java.sql.SQLException;
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
      return productRepository.list();
    }
  }

  @Override
  public List<Category> listCategory() throws SQLException {
    try (Connection connection = DBConnection.getConnection()) {
      categoryRepository.setConnection(connection);
      return categoryRepository.list();
    }
  }

  @Override
  public Product productById(Long id) throws SQLException {
    try (Connection connection = DBConnection.getConnection()) {
      productRepository.setConnection(connection);
      return productRepository.byId(id);
    }
  }

  @Override
  public Category categoryById(Long id) throws SQLException {
    try (Connection connection = DBConnection.getConnection()) {
      categoryRepository.setConnection(connection);
      return categoryById(id);
    }
  }

  @Override
  public Product saveProduct(Product product) throws SQLException {
    try (Connection connection = DBConnection.getConnection()) {
      productRepository.setConnection(connection);
      if (connection.getAutoCommit()) {
        connection.setAutoCommit(false);
      }

      Product newProduct = null;
      try {
        newProduct = productRepository.save(product);
        connection.commit();
      } catch (SQLException e) {
        connection.rollback();
        e.printStackTrace();
      }

      return newProduct;
    }
  }

  @Override
  public Category saveCategory(Category category) throws SQLException {
    try (Connection connection = DBConnection.getConnection()) {
      categoryRepository.setConnection(connection);
      if (connection.getAutoCommit()) {
        connection.setAutoCommit(false);
      }

      Category newCategory = null;
      try {
        newCategory = categoryRepository.save(category);
        connection.commit();
      } catch (SQLException e) {
        connection.rollback();
        e.printStackTrace();
      }

      return newCategory;
    }
  }

  @Override
  public void deleteProduct(Long id) throws SQLException {
    try (Connection connection = DBConnection.getConnection()) {
      productRepository.setConnection(connection);
      if (connection.getAutoCommit()) {
        connection.setAutoCommit(false);
      }

      try {
        productRepository.remove(id);
        connection.commit();
      } catch (SQLException e) {
        connection.rollback();
        e.printStackTrace();
      }
    }

  }

  @Override
  public void deleteCategory(Long id) throws SQLException {
    try (Connection connection = DBConnection.getConnection()) {
      categoryRepository.setConnection(connection);
      if (connection.getAutoCommit()) {
        connection.setAutoCommit(false);
      }

      try {
        categoryRepository.remove(id);
        connection.commit();
      } catch (SQLException e) {
        connection.rollback();
        e.printStackTrace();
      }
    }
  }

  @Override
  public void saveProductWithCategory(Product product, Category category) throws SQLException {
    try (Connection connection = DBConnection.getConnection()) {
      productRepository.setConnection(connection);
      categoryRepository.setConnection(connection);

      if (connection.getAutoCommit()) {
        connection.setAutoCommit(false);
      }

      try {
        Category newCategory = categoryRepository.save(category);
        product.setCategory(newCategory);
        productRepository.save(product);
        connection.commit();
      } catch (SQLException e) {
        connection.rollback();
        e.printStackTrace();
      }

    }
  }

}
