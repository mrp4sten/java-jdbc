package com.mrp4sten.org.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
  private static String nameDataBase = System.getenv("JDBC_DATA_BASE_NAME");
  private static String url = System.getenv("JDBC_URL") + nameDataBase;
  private static String username = System.getenv("JDBC_USERNAME");
  private static String password = System.getenv("JDBC_PASSWORD");

  private static Connection connection;

  public static Connection getInstance() throws SQLException {
    if (connection == null) {
      connection = DriverManager.getConnection(url, username, password);
    }

    return connection;
  }
}
