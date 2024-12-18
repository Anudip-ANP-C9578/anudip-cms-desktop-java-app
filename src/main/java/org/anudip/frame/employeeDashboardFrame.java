package org.anudip.frame;

import javax.swing.*;
import java.awt.*;

public class employeeDashboardFrame extends JFrame {
    private JLabel welcomeLabel;

    public employeeDashboardFrame(String employeeName) {
        setTitle("Employee Dashboard");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set the layout
        setLayout(new BorderLayout());
        
        // Create and add the welcome label with the employee's name
        welcomeLabel = new JLabel("Welcome to the Employee Dashboard, " + employeeName + "!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 20));
        
        add(welcomeLabel, BorderLayout.CENTER);
        
        setVisible(true);
    }
}
