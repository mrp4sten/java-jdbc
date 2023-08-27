package com.mrp4sten.org.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
  List<T> list() throws SQLException;

  T byId(Long id) throws SQLException;

  T save(T t) throws SQLException;

  void remove(Long id) throws SQLException;

  void setConnection(Connection connection);
}
