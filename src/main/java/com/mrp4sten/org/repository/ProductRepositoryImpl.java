package com.mrp4sten.org.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mrp4sten.org.model.Category;
import com.mrp4sten.org.model.Product;

public class ProductRepositoryImpl implements Repository<Product> {

  private Connection connection;

  @Override
  public void setConnection(Connection connection) {
    this.connection = connection;
  }

  public ProductRepositoryImpl(Connection connection) {
    this.connection = connection;
  }

  public ProductRepositoryImpl() {
  }

  private Product getProduct(ResultSet resultSet) throws SQLException {
    Product product = new Product();

    product.setId(resultSet.getLong("id"));
    product.setName(resultSet.getString("name"));
    product.setPrice(resultSet.getDouble("price"));
    product.setRecordDate(resultSet.getDate("record_date"));
    Category category = new Category();
    category.setId(resultSet.getLong("category_id"));
    category.setName(resultSet.getString("category"));
    product.setCategory(category);
    product.setSku(resultSet.getString("sku"));

    return product;
  }

  @Override
  public List<Product> list() throws SQLException {
    List<Product> products = new ArrayList<>();

    String query = "SELECT p.*, c.name AS category  FROM products AS p INNER JOIN categories as c ON (p.category_id = c.id)";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          Product product = getProduct(resultSet);

          products.add(product);
        }
      }
    }

    return products;
  }

  @Override
  public Product byId(Long id) throws SQLException {
    Product product = null;
    String query = "SELECT p.*, c.name AS category FROM products AS p INNER JOIN categories as c ON (p.category_id = c.id) where p.id = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setLong(1, id);
      try (ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
          product = getProduct(resultSet);
        }
      }
    }
    return product;

  }

  @Override
  public Product save(Product t) throws SQLException {
    boolean isUpdateProduct = t.getId() != null && t.getId() > 0;
    String query = "";
    if (isUpdateProduct) {
      query = "UPDATE products SET name=?, price=?, category_id=?, sku=? WHERE id=?";
    } else {
      query = "INSERT INTO products(name, price, category_id, sku, record_date) VALUES(?,?,?,?,?)";
    }

    try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
      statement.setString(1, t.getName());
      statement.setDouble(2, t.getPrice());
      statement.setLong(3, t.getCategory().getId());
      statement.setString(4, t.getSku());

      if (isUpdateProduct) {
        statement.setLong(5, t.getId());
      } else {
        statement.setDate(5, t.getRecordDate());
      }
      statement.executeUpdate();

      if (t.getId() == null) {
        try (ResultSet resultSet = statement.getGeneratedKeys()) {
          if (resultSet.next()) {
            t.setId(resultSet.getLong(1));
          }
        }
      }

      return t;
    }
  }

  @Override
  public void remove(Long id) throws SQLException {
    String query = "DELETE FROM products WHERE id=?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setLong(1, id);
      statement.executeUpdate();

    }
  }

}
