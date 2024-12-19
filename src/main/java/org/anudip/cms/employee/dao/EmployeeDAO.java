package org.anudip.cms.employee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.anudip.cms.employee.entity.EmployeeEntity;


public class EmployeeDAO {
	private Connection conn;
	private PreparedStatement stmt;

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/employee_information";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root@12345";

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
    public EmployeeEntity verifyCredentials(String email_ID, String emp_pass) {
        String query = "SELECT * FROM employee WHERE email_ID = ? AND emp_pass = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, email_ID);
            stmt.setString(2, emp_pass);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return new EmployeeEntity(
                    rs.getInt("Id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email_ID"),
                    rs.getString("emp_pass")
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