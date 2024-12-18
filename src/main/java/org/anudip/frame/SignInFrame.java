package org.anudip.frame;

import org.anudip.dao.EmployeeDAO;
import org.anudip.entity.EmployeeEntity;
import org.anudip.frame.EmployeeDashboardFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInFrame extends JFrame {
    private JTextField email_idField;
    private JPasswordField passwordField;
    private EmployeeDAO employeeDAO;

    public SignInFrame() {
        // Initialize DAO for database access
        employeeDAO = new EmployeeDAO();

        // Frame setup
        setTitle("Employee Login");
        setLayout(new FlowLayout());
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create email and password fields
        email_idField = new JTextField(20);
        passwordField = new JPasswordField(20);

        // Create Sign In and Clear buttons
        JButton signInButton = new JButton("Sign In");
        JButton clearButton = new JButton("Clear");

        // Add components to the frame
        add(new JLabel("Email_id:"));
        add(email_idField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(signInButton);
        add(clearButton);

        // Sign In button action
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email_id = email_idField.getText();
                String password = new String(passwordField.getPassword());

                // Verify credentials
                if (employeeDAO.verifyCredentials(email_id, password)) {
                    EmployeeEntity employee = employeeDAO.getEmployeeByEmail(email_id);
                    JOptionPane.showMessageDialog(null,"Welcome " + employee.getfirst_name() + " " + employee.getlast_name());
                    // Open Employee Dashboard
                    openEmployeeDashboard(employee);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Access. Try Again.");
                }
            }
        });

        // Clear button action
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                email_idField.setText("");
                passwordField.setText("");
            }
        });
    }

    // Open Employee Dashboard after successful login
    private void openEmployeeDashboard(EmployeeEntity employee) {
        // Open the employee dashboard and pass the employee object
        EmployeeDashboardFrame dashboard = new EmployeeDashboardFrame(employee); // Pass the employee object
        dashboard.setVisible(true);
        this.setVisible(false); // Hide the SignInFrame after successful login
    }

    // Main method (entry point for the application)
    public static void main(String[] args) {
        // Create an instance of SignInFrame and make it visible
        SignInFrame signInFrame = new SignInFrame();
        signInFrame.setVisible(true);
    }
}
