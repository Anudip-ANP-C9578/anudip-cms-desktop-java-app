package org.anudip.dao;

import org.anudip.entity.EmployeeEntity;

import java.sql.*;

import java.sql.*;

public class EmployeeDAO {
    private Connection connection;

    // Constructor to initialize the database connection
    public EmployeeDAO() {
        try {
            connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/java project", "root", "Thanubc@123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to verify credentials
    public boolean verifyCredentials(String email, String password) {
        String query = "SELECT * FROM employee WHERE email_id = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next(); // If employee exists with the given credentials
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to retrieve employee by email
    public EmployeeEntity getEmployeeByEmail(String email_id) {
        String query = "SELECT * FROM employee WHERE email_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email_id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new EmployeeEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email_id"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // No employee found with the given email
    }
}


