package org.anudip.dao;

import org.anudip.entity.EmployeeEntity;

import java.sql.*;

public class EmployeeDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/java project";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Thanubc@123";

    // Method to create a database connection
    private Connection createDbConnection() throws SQLException {
        System.out.println("Creating database connection...");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Method to close database connection
    private void closeDbConnection(Connection dbConn) {
        try {
            if (dbConn != null) {
                dbConn.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing database connection: " + e.getMessage());
        }
    }

    // Method to create a Statement
    private Statement createDbStatement(Connection dbConn) throws SQLException {
        System.out.println("Creating Statement...");
        return dbConn.createStatement();
    }

    // Method to close Statement
    private void closeDbStatement(Statement dbStmt) {
        try {
            if (dbStmt != null) {
                dbStmt.close();
                System.out.println("Statement closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing Statement: " + e.getMessage());
        }
    }

    // Method to close ResultSet
    private void closeDbResultSet(ResultSet dbRs) {
        try {
            if (dbRs != null) {
                dbRs.close();
                System.out.println("ResultSet closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing ResultSet: " + e.getMessage());
        }
    }

    // Method to close all resources: Connection, Statement, ResultSet
    private void closeAllDbResources(Connection dbConn, Statement dbStmt, ResultSet dbRs) {
        closeDbResultSet(dbRs);
        closeDbStatement(dbStmt);
        closeDbConnection(dbConn);
    }

    // Method to verify credentials (returns boolean indicating success)
    public boolean verifyCredentials(String email_id, String password) {
        String query = "SELECT * FROM employee WHERE email_id = '" + email_id + "' AND password = '" + password + "'";
        Connection dbConn = null;
        Statement dbStmt = null;
        ResultSet dbRs = null;

        try {
            dbConn = createDbConnection(); // Create the connection
            dbStmt = createDbStatement(dbConn); // Create statement
            dbRs = dbStmt.executeQuery(query); // Execute the query

            if (dbRs != null && dbRs.next()) {
                System.out.println("Credentials verified successfully.");
                return true; // Valid credentials
            }
        } catch (SQLException e) {
            System.out.println("Error verifying credentials: " + e.getMessage());
            e.printStackTrace();  // Print the full stack trace for debugging
        } finally {
            closeAllDbResources(dbConn, dbStmt, dbRs); // Close all resources
        }
        System.out.println("Invalid credentials.");
        return false; // Invalid credentials
    }

    // Method to retrieve employee details by email (returns EmployeeEntity)
    public EmployeeEntity getEmployeeByEmail(String email_id) {
        String query = "SELECT * FROM employee WHERE email_id = '" + email_id + "'";
        Connection dbConn = null;
        Statement dbStmt = null;
        ResultSet dbRs = null;

        try {
            dbConn = createDbConnection(); // Create the connection
            dbStmt = createDbStatement(dbConn); // Create statement
            dbRs = dbStmt.executeQuery(query); // Execute the query

            if (dbRs != null && dbRs.next()) {
                System.out.println("Employee found: " + dbRs.getString("first_name") + " " + dbRs.getString("last_name"));
                return new EmployeeEntity(
                        dbRs.getInt("id"),
                        dbRs.getString("first_name"),
                        dbRs.getString("last_name"),
                        dbRs.getString("email_id"),
                        dbRs.getString("password")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving employee: " + e.getMessage());
            e.printStackTrace();  // Print the full stack trace for debugging
        } finally {
            closeAllDbResources(dbConn, dbStmt, dbRs); // Close all resources
        }
        System.out.println("No employee found with the given email.");
        return null; // No employee found with the given email
    }

    // Method to add a student (promoting the functionality of adding a student)
    public boolean addStudent(String firstName, String lastName, String email, String course) {
        String query = "INSERT INTO students (first_name, last_name, email, course) VALUES ('" + firstName + "', '" + lastName + "', '" + email + "', '" + course + "')";
        Connection dbConn = null;
        Statement dbStmt = null;

        try {
            dbConn = createDbConnection(); // Create the connection
            dbStmt = createDbStatement(dbConn); // Create statement
            int rowsAffected = dbStmt.executeUpdate(query); // Execute the insert query

            if (rowsAffected > 0) {
                System.out.println("Student added successfully.");
                return true; // If at least one row was inserted, return true
            } else {
                System.out.println("Failed to add student.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
            e.printStackTrace();  // Print the full stack trace for debugging
        } finally {
            closeDbStatement(dbStmt); // Close statement
            closeDbConnection(dbConn); // Close connection
        }
        return false; // Failure to add student
    }
}
