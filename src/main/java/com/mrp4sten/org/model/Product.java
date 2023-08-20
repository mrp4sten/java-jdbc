package com.mrp4sten.org.model;

import java.sql.Date;

public class Product {
  private Long id;
  private String name;
  private Double price;
  private Date recordDate;

  public Product(Long id, String name, Double price, Date recordDate) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.recordDate = recordDate;
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

}
