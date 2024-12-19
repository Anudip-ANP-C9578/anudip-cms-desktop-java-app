package org.anudip.dao;

import org.anudip.entity.EmployeeEntity;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class EmployeeDAO {
    private Connection dbConn = null;
    private Statement dbStmt = null;

    private void loadDriverClass() { // Load MySQL JDBC Driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnfEx) {
            System.err.println("MySQL JDBC Driver not found!");
            cnfEx.printStackTrace(); // Corrected the variable name here
        }
    }

    private void createDBConnection() {
        loadDriverClass();
        try {
            dbConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/anudip_db", "root", "root");
            dbStmt = dbConn.createStatement();
            System.out.println("Database connected successfully!");
        } catch (SQLException sqlEx) {
            System.err.println("Database connection failed!");
            sqlEx.printStackTrace();
        }
    }
    private Statement createDBStatement() {
        try {
            if (dbStmt != null) {
                dbStmt.close();
            }
        } catch (SQLException sqlEx) {
            System.err.println("Error creating statement!");
            sqlEx.printStackTrace();
        }
        return dbStmt;
    }

    private void closeDBStatement() {
        try {
            if (dbStmt != null)
                dbStmt.close();
        } catch (SQLException sqlEx) {
            System.err.println("Error closing statement!");
            sqlEx.printStackTrace();
        }
    }
    private void closeDBResult(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException sqlEx) {
            System.err.println("Error closing ResultSet!");
            sqlEx.printStackTrace();
        }
    }
    // Close the database connection
    public void closeDBConnection() {
        try {
            if (dbStmt != null) {
                closeDBStatement();
            }
            if (dbConn != null) {
                dbConn.close(); // Make sure the connection is closed
            }
        } catch (SQLException sqlEx) {
            System.err.println("Error closing database connection!");
            sqlEx.printStackTrace();
        }
    }
    public String validateEmployee(String emailAddress, String password) {
        String employeeName = null;
        
        // Ensure that the connection is created if it's not already established
        if (dbConn == null) {
            createDBConnection();  // Ensure the connection is established
        }

        String query = "SELECT first_name, last_name FROM employee WHERE email_address = ? AND password = ?";

        try (PreparedStatement pstmt = dbConn.prepareStatement(query)) {
            pstmt.setString(1, emailAddress);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) { // Use try-with-resources for ResultSet
                if (rs.next()) {
                    // Concatenate first and last names
                    employeeName = rs.getString("first_name") + " " + rs.getString("last_name");
                }
            } catch (SQLException rsEx) {
                System.err.println("Error processing ResultSet");
                rsEx.printStackTrace();
            }
        } catch (SQLException e) {
            System.err.println("Error executing query");
            e.printStackTrace();
        }

        return employeeName; // Return the name or null if validation fails
    }
}
    




        
	
	
	


