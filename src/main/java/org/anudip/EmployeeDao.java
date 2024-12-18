package org.anudip;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class EmployeeDao {

	private Connection dbConn = null;
	private Statement dbStmt = null;

	// Constructor to initialize the database connection
	public EmployeeDao() {
		try {
			// Load MySQL JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish database connection
			dbConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ameeda_db", "root", "2003@Ame");

			// Create a Statement object
			dbStmt = dbConn.createStatement();

		} catch (ClassNotFoundException e) {
			System.err.println("MySQL JDBC Driver not found!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Database connection failed!");
			e.printStackTrace();
		}
	}

	// Validate employee credentials and return their name if successful
	public String validateCredentials(String email, String password) {
		String employeeName = null;
		String query = "SELECT first_name, last_name FROM employee WHERE email_id = ? AND password = ?";

		try (PreparedStatement pstmt = dbConn.prepareStatement(query)) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				// Concatenate first and last names
				employeeName = rs.getString("first_name") + " " + rs.getString("last_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employeeName; // Return the name or null if validation fails
	}

	// Close the database connection
	public void closeConnection() {
		try {
			if (dbStmt != null)
				dbStmt.close();
			if (dbConn != null)
				dbConn.close();
		} catch (SQLException e) {
			System.err.println("Error closing database connection!");
			e.printStackTrace();
		}
	}
}