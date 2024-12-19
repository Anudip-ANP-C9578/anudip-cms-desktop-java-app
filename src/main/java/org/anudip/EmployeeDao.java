package org.anudip;

import java.sql.*;

// Handles database interactions for Employee entity
public class EmployeeDao {

	private Connection dbConn = null; // Database connection object
	private Statement dbStmt = null; // Statement object for queries

	// Method to load JDBC Driver Class
	private void loadDriverClass() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Load JDBC driver
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		}
	}

	// Method to create database connection
	private void createDBConnection() {
		loadDriverClass(); // Load driver

		try {
			dbConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/newdata", "root", "Deek@123"); // Connect
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}

	// Method for database statement creation
	private Statement createDBStatement() {
		if (dbConn == null) {
			createDBConnection(); // Ensure connection is established
		}

		try {
			dbStmt = dbConn.createStatement(); // Create a statement
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}

		return dbStmt;
	}

	// Method to close database statement
	private void closeDBStatement() {
		try {
			if (dbStmt != null) {
				dbStmt.close();
			}
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}

	// Method to close result set
	private void closeDBResultSet(ResultSet dbRs) {
		try {
			if (dbRs != null) {
				dbRs.close();
			}
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}

	// Method to close database connection
	private void closeDBConnection() {
		try {
			if (dbConn != null) {
				dbConn.close();
			}
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}

	// Method to verify credentials
	public String validateCredentials(String email, String password) {
		String employeeName = null;

		// Ensure connection is established
		if (dbConn == null) {
			createDBConnection();
		}

		// SQL query to validate credentials
		String query = "SELECT first_name, last_name FROM employee WHERE email_id = ? AND password = ?";

		try (PreparedStatement pstmt = dbConn.prepareStatement(query)) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			ResultSet dbRs = pstmt.executeQuery();
			if (dbRs.next()) {
				// Concatenate first and last names
				employeeName = dbRs.getString("first_name") + " " + dbRs.getString("last_name");
			}
			closeDBResultSet(dbRs); // Close result set
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}

		// Return name or null
		return employeeName;
	}

	// Method to close resources
	public void closeResources() {
		closeDBStatement(); // Close statement
		closeDBConnection(); // Close connection
	}
}