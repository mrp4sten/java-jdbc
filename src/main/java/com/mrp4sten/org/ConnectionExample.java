package com.mrp4sten.org;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mrp4sten.org.util.DBConnection;

public class ConnectionExample {
    public static void main(String[] args) {
        try {
            try (Connection connection = DBConnection.getInstance()) {
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