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
				dbConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employee_db", "root", "Vaibhav@1907");

				Statement dbStmt = dbConn.createStatement();

			} catch (ClassNotFoundException cnfEx) {
				cnfEx.printStackTrace();
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}

		}

		public Employee verifyCredentials(String emailID, String emp_password) {
			
			String query = "SELECT * FROM employee_info WHERE emailID='" + emailID + "'AND emp_password='" +emp_password +"';";

			try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			

				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					return new Employee(rs.getInt("Id"), rs.getString("first_name"), rs.getString("last_name"),
							rs.getString("emailID"), rs.getString("emp_password"));
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


