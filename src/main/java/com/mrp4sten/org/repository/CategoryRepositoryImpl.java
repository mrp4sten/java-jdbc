package com.mrp4sten.org.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mrp4sten.org.model.Category;

public class CategoryRepositoryImpl implements Repository<Category> {

  private Connection connection;

  @Override
  public void setConnection(Connection connection) {
    this.connection = connection;
  }

  public CategoryRepositoryImpl(Connection connection) {
    this.connection = connection;
  }

  public CategoryRepositoryImpl() {
  }

  private Category makeCategory(ResultSet resultSet) throws SQLException {
    return new Category(resultSet.getLong("id"), resultSet.getString("name"));
  }

  @Override
  public List<Category> list() throws SQLException {
    List<Category> categories = new ArrayList<>();
    String query = "SELECT * FROM categories";
    try (Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query)) {

      while (resultSet.next()) {
        categories.add(makeCategory(resultSet));
      }

      return categories;
    }
  }

  @Override
  public Category byId(Long id) throws SQLException {
    Category category = null;
    String query = "SELECT * FROM categories as c WHERE c.id = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setLong(1, id);
      try (ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
          category = makeCategory(resultSet);
        }

        return category;
      }
    }
  }

  @Override
  public Category save(Category t) throws SQLException {
    boolean isUpdateCategory = t.getId() != null && t.getId() > 0;
    String sql = null;

    if (isUpdateCategory) {
      sql = "UPDATE categories SET name=? WHERE id=?";
    } else {
      sql = "INSERT INTO categories(name) VALUES(?)";
    }

    try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      preparedStatement.setString(1, t.getName());
      if (isUpdateCategory) {
        preparedStatement.setLong(2, t.getId());
      }

      preparedStatement.executeUpdate();

      if (t.getId() == null) {
        try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
          if (resultSet.next()) {
            t.setId(resultSet.getLong(1));
          }
        }
      }
    }

    return t;

  }

  @Override
  public void remove(Long id) throws SQLException {
    try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM categories WHERE id=?")) {
      preparedStatement.setLong(1, id);
      preparedStatement.executeUpdate();
    }
  }

}
