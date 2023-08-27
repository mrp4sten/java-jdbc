package com.mrp4sten.org.service;

import java.sql.SQLException;
import java.util.List;

import com.mrp4sten.org.model.Category;
import com.mrp4sten.org.model.Product;

public interface Service {
  List<Product> listProduct() throws SQLException;

  List<Category> listCategory() throws SQLException;

  Product productById(Long id) throws SQLException;

  Category categoryById(Long id) throws SQLException;

  Product saveProduct(Product product) throws SQLException;

  Category saveCategory(Category category) throws SQLException;

  void deleteProduct(Long id) throws SQLException;

  void deleteCategory(Long id) throws SQLException;

  void saveProductWithCategory(Product product, Category category) throws SQLException;
}
