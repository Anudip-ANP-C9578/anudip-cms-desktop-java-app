//DAO  class

package org.anudip.cms.employee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.anudip.cms.employee.dao.EmployeeDAO;
import org.anudip.cms.employee.entity.Employee;

public class EmployeeDAO {

	public EmployeeDAO() {
		createDBConnection();
	}

	private Connection dbConn;
	private Statement dbStmt;
	private ResultSet dbResultSet;

	private void loadDriverClass() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		}
	}

	private void createDBConnection() {
		try {
			loadDriverClass();
			dbConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/anudip_cms_db", "root", "root");
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}

	private Statement createStatement() {
		try {
			if (dbConn != null)
				dbConn.close();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return dbStmt;
	}

	private void closeDBStatement() {
		try {
			if (dbStmt != null)
				dbStmt.close();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}

	private void closeDBResultSet() {
		try {
			if (dbResultSet != null)
				dbResultSet.close();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}
	
	public void closeDBConnection() {
		try {
			if (dbConn != null)
				dbConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Employee verifyCredentials(String email_address, String password) {
		// createDBConnection();
		String query = "SELECT * FROM employee WHERE email_address='" + email_address + "' AND password='" + password
				+ "';";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Employee(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("email_address"), rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDBResultSet();
			closeDBStatement();
			closeDBConnection();
		}
		return null;
	}
}
