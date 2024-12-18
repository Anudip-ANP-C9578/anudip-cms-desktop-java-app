package org.anudip.frame;

import org.anudip.dao.EmployeeDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SignInFrame extends JFrame implements ActionListener {
    private JButton jButton1, jButton2;
    private JTextField tfUserName;      // Instance variable
    private JPasswordField pfPassword; // Instance variable

    public SignInFrame() {
        setTitle("SignIn - Anudip Foundation Content Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);

        JPanel jPanel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        jPanel.setLayout(gridBagLayout);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        JLabel greetingLabel = new JLabel("Welcome to Anudip");
        JLabel lblUserName = new JLabel("username");
        JLabel lblPassword = new JLabel("Password");

        // Initialize instance variables
        tfUserName = new JTextField(20); 
        pfPassword = new JPasswordField(20); 

        jButton1 = new JButton("SignIn");
        jButton1.setPreferredSize(new Dimension(200, 30)); 
        jButton1.addActionListener(this);

        jButton2 = new JButton("Clear");
        jButton2.setPreferredSize(new Dimension(200, 30));
        jButton2.addActionListener(this);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        jPanel.add(greetingLabel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        jPanel.add(lblUserName, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel.add(tfUserName, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel.add(lblPassword, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        jPanel.add(pfPassword, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        jPanel.add(jButton1, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        jPanel.add(jButton2, gridBagConstraints);

        Container container = getContentPane();
        container.add(jPanel);

        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        EmployeeDAO employeeDAO = new EmployeeDAO(); // Initialize DAO

        if (e.getSource() == jButton1) { // SignIn button clicked
            String emailId = tfUserName.getText();
            String password = new String(pfPassword.getPassword());

            String employeeName = employeeDAO.validateEmployee(emailId, password); // Get employee name

            if (employeeName != null) {
                // First pop-up: Welcome to the Employee Dashboard with employee name
                JOptionPane.showMessageDialog(this,
                        "Welcome , " + employeeName + "!",
                        "SignIn Success",
                        JOptionPane.INFORMATION_MESSAGE);

                // Show the second pop-up: Welcome to Anudip
                /*JOptionPane.showMessageDialog(this,
                        "Welcome to Anudip!",
                        "Welcome",
                        JOptionPane.INFORMATION_MESSAGE);*/

                // Close the current SignIn frame
                this.dispose();
                
                // Open EmployeeDashboard window
                new EmployeeDashboardFrame(employeeName);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Invalid Email ID or Password. Try Again.",
                        "SignIn Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == jButton2) { // Clear button clicked
            tfUserName.setText(""); // Clear username/email field
            pfPassword.setText(""); // Clear password field
        }
    }
}

