package com.mrp4sten.org;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionExample {
    public static void main(String[] args) {
        String nameDataBase = System.getenv("JDBC_DATA_BASE_NAME");
        String url = System.getenv("JDBC_URL") + nameDataBase;
        String username = System.getenv("JDBC_USERNAME");
        String password = System.getenv("JDBC_PASSWORD");

        try {
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                String query = "SELECT * FROM products";
                try (PreparedStatement statement = connection.prepareStatement(query);
                        ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        System.out.print(resultSet.getString("id"));
                        System.out.print(" | ");
                        System.out.print(resultSet.getString("name"));
                        System.out.print(" | ");
                        System.out.print(resultSet.getDouble("price"));
                        System.out.print(" | ");
                        System.out.println(resultSet.getDate("record_date"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}