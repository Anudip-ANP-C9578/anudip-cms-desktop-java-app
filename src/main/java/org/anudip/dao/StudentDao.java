package org.anudip.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.anudip.entity.Student;

public class StudentDao {
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
	private Connection createDBConnection() {
		loadDriverClass();

		try {
			dbConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ameeda_db", "root", "2003@Ame");
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return dbConn;
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

	public boolean insertStudent(Student student) {
		boolean status = false;

		// To create database connection if it is not connected

		if (dbConn == null) {
			dbConn = createDBConnection();
		}

		String sqlQuery = "INSERT INTO student (first_name, last_name, date_of_birth, gender) VALUES (?, ?, ?, ?)";

		try (PreparedStatement pstmt = dbConn.prepareStatement(sqlQuery)) {
			pstmt.setString(1, student.getFirstName());
			pstmt.setString(2, student.getLastName());
			pstmt.setString(3, student.getDateOfBirth());
			pstmt.setString(4, student.getGender());

			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				status = true;
			}
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			closeDBConnection();
		}
		return status;
	}
}