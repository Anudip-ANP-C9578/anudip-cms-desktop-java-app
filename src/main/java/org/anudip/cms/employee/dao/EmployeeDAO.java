package org.anudip.cms.employee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.anudip.cms.employee.entity.Employee;


public class EmployeeDAO {
	private Connection conn;
	private PreparedStatement stmt;

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/desktop_app";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Khushi#1122005";

    // Method to establish a database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Method to close the connection
    private void closeConnection(Connection conn) {
        try{
        	if (conn != null) {
                conn.close();
        	}
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
    }

    // Method to close the PreparedStatement
    private void closeStatement(PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to close the ResultSet
    private void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to verify credentials
    public Employee verifyCredentials(String email_id, String emp_password) {
        String query = "SELECT * FROM employee WHERE email_id = ? AND emp_password = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, email_id);
            stmt.setString(2, emp_password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return new Employee(
                    rs.getInt("emp_ID"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email_id"),
                    rs.getString("emp_password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
            closeStatement(stmt);
            closeConnection(conn);
        }

        return null;
    }
}
