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
	private Connection dbConn;

	public EmployeeDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/anudip_cms_db", "root", "root");

			Statement dbStmt = dbConn.createStatement();

		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}

	}

	public Employee verifyCredentials(String email_address, String password) {
		//String query = "SELECT * FROM employee WHERE email_address = ? AND password = ?";
		String query = "SELECT * FROM employee WHERE email_address='" + email_address + "' AND password='" + password +"';";

		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			//stmt.setString(1, email_address);
			//stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Employee(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("email_address"), rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void closeConnection() {
		try {
			if (dbConn != null)
				dbConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
