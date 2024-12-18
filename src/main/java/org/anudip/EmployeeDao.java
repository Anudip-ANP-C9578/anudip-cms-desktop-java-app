package org.anudip;

import java.sql.*;

// Handles database interactions for Employee entity
public class EmployeeDao {
	private Connection dbConn = null; // Database connection object
	private Statement dbStmt = null; // Statement object for queries

	// Constructor to initialize the database connection
	public EmployeeDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Load JDBC driver
			dbConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/newdata", "root", "Deek@123"); // Connect
																												// to
																												// the
																												// database
			dbStmt = dbConn.createStatement(); // Create a statement object
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	// Validates credentials and returns employee's full name
	public String validateCredentials(String email, String password) {
		String query = "SELECT first_name, last_name FROM employee WHERE email_id = ? AND password = ?";
		try (PreparedStatement pstmt = dbConn.prepareStatement(query)) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("first_name") + " " + rs.getString("last_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Closes the database connection
	public void closeConnection() {
		try {
			if (dbStmt != null)
				dbStmt.close();
			if (dbConn != null)
				dbConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}