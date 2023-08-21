package com.mrp4sten.org.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBConnection {
  private static String nameDataBase = System.getenv("JDBC_DATA_BASE_NAME");
  private static String url = System.getenv("JDBC_URL") + nameDataBase;
  private static String username = System.getenv("JDBC_USERNAME");
  private static String password = System.getenv("JDBC_PASSWORD");

  private static BasicDataSource pool;

  public static BasicDataSource getInstance() throws SQLException {
    if (pool == null) {
      pool = new BasicDataSource();
      pool.setUrl(url);
      pool.setUsername(username);
      pool.setPassword(password);

      pool.setInitialSize(3);
      pool.setMinIdle(3);
      pool.setMaxIdle(8);
      pool.setMaxTotal(8);
    }

    return pool;
  }

  public static Connection getConnection() throws SQLException {
    return getInstance().getConnection();
  }
}
