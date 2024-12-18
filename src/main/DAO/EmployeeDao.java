import java.sql.*;

public class EmployeeDao {
    private Connection connection;

    public EmployeeDao() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");
    }

    public Employee validateCredentials(String email, String password) throws SQLException {
        String query = "SELECT * FROM Employee WHERE email_id = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email_id"),
                        resultSet.getString("password")
                    );
                }
            }
        }
        return null;
    }

    public void closeConnection() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}