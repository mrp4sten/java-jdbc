package com.mrp4sten.org.model;

import java.sql.Date;

public class Product {
  private Long id;
  private String name;
  private Double price;
  private Date recordDate;
  private Category category;
  private String sku;

  public Product(Long id, String name, Double price, Date recordDate, Category category, String sku) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.recordDate = recordDate;
    this.category = category;
    this.sku = sku;
  }

  public Product() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Date getRecordDate() {
    return recordDate;
  }

  public void setRecordDate(Date recordDate) {
    this.recordDate = recordDate;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  @Override
  public String toString() {
    return "Product [id=" + id + ", name=" + name + ", price=" + price + ", recordDate=" + recordDate + ", category="
        + category + ", sku=" + sku + "]";
  }

}
