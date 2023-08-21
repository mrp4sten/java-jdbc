package com.mrp4sten.org.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mrp4sten.org.model.Product;
import com.mrp4sten.org.util.DBConnection;

public class ProductRepositoryImpl implements Repository<Product> {

  private Connection getConnection() throws SQLException {
    return DBConnection.getInstance();
  }

  private Product getProduct(ResultSet resultSet) throws SQLException {
    Product product = new Product();

    product.setId(resultSet.getLong("id"));
    product.setName(resultSet.getString("name"));
    product.setPrice(resultSet.getDouble("price"));
    product.setRecordDate(resultSet.getDate("record_date"));

    return product;
  }

  @Override
  public List<Product> list() {
    List<Product> products = new ArrayList<>();

    String query = "SELECT * FROM products";
    try (PreparedStatement statement = getConnection().prepareStatement(query)) {
      try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          Product product = getProduct(resultSet);

          products.add(product);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return products;
  }

  @Override
  public Product byId(Long id) {
    Product product = null;
    String query = "SELECT * FROM products where id = ?";
    try (PreparedStatement statement = getConnection().prepareStatement(query)) {
      statement.setLong(1, id);
      try (ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
          product = getProduct(resultSet);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return product;

  }

  @Override
  public void save(Product t) {
    boolean isUpdateProduct = t.getId() != null && t.getId() > 0;
    String query = "";
    if (isUpdateProduct) {
      query = "UPDATE products SET name=?, price=? WHERE id=?";
    } else {
      query = "INSERT INTO products(name, price, record_date) VALUES(?,?,?)";
    }

    try (PreparedStatement statement = getConnection().prepareStatement(query)) {
      statement.setString(1, t.getName());
      statement.setDouble(2, t.getPrice());

      if (isUpdateProduct) {
        statement.setLong(3, t.getId());
      } else {
        statement.setDate(3, t.getRecordDate());
      }
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void remove(Long id) {
    String query = "DELETE FROM products WHERE id=?";
    try (PreparedStatement statement = getConnection().prepareStatement(query)) {
      statement.setLong(1, id);
      statement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
