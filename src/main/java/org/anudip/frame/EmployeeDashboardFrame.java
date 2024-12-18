package org.anudip.frame;
import org.anudip.entity.EmployeeEntity;
import javax.swing.*;
import java.awt.*;

public class EmployeeDashboardFrame extends JFrame {

    // Constructor that accepts an EmployeeEntity object
    public EmployeeDashboardFrame(EmployeeEntity employee) {
        setTitle("Employee Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a welcome message with the employee's full name
        JLabel welcomeLabel = new JLabel("Welcome to the Employee Dashboard, " + employee.getfirst_name() + " " + employee.getlast_name() + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        // Set up the layout and add components
        setLayout(new BorderLayout());
        add(welcomeLabel, BorderLayout.CENTER);

        // Optional: Add a logout button or other dashboard components
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            // Close the current dashboard and go back to SignInFrame (Login screen)
            dispose();
            SignInFrame signInFrame = new SignInFrame();
            signInFrame.setVisible(true);
        });
        
    }
    public static void main(String[] args) {
        // Example to test the dashboard with a sample EmployeeEntity
        EmployeeEntity employee = new EmployeeEntity(1, "John", "Doe", "john.doe@example.com", "password");
        EmployeeDashboardFrame dashboard = new EmployeeDashboardFrame(employee);
        dashboard.setVisible(true);
    }
}
