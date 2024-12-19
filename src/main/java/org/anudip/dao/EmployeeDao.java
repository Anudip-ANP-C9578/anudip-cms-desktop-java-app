package org.anudip.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class EmployeeDao {

	private Connection dbConn = null;
	private Statement dbStmt = null;

	// Method to load JDBC Driver Class
	private void loadDriverClass() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		}
	}

	// Method to create database connection
	private void createDBConnection() {
		loadDriverClass();

		try {
			dbConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ameeda_db", "root", "2003@Ame");
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}

	// Method for database statement creation
	private Statement createDBStatement() {
		createDBConnection();

		try {
			dbStmt = dbConn.createStatement();
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

	// Method to verify the credentials are matching or not
	public String validateCredentials(String email, String password) {
		String employeeName = null;

		// To create database connection if it is not connected
		if (dbConn == null) {
			createDBConnection();
		}

		// SQL query to select first name and last name
		String query = "SELECT first_name, last_name FROM employee WHERE email_id = ? AND password = ?";

		try (PreparedStatement pstmt = dbConn.prepareStatement(query)) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			ResultSet dbRs = pstmt.executeQuery();
			if (dbRs.next()) {
				// Concatenate first and last names
				employeeName = dbRs.getString("first_name") + " " + dbRs.getString("last_name");
			}
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}

		// Return the name or null if validation fails
		return employeeName;
	}
}