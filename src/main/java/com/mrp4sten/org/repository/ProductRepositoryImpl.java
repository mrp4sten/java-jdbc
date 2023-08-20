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

  @Override
  public List<Product> list() {
    List<Product> products = new ArrayList<>();

    try (Connection connection = getConnection()) {
      String query = "SELECT * FROM products";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        try (ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getLong("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getDouble("price"));
            product.setRecordDate(resultSet.getDate("record_date"));

            products.add(product);
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return products;
  }

  @Override
  public Product byId() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'byId'");
  }

  @Override
  public void save(Product t) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }

  @Override
  public void remove(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'remove'");
  }

}
