package org.anudip.cms.employee.frame;

// class for employee dashboard

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class OpenEmployeeDashboard extends JFrame {

	public void openEmployeeDashboard(String first_name, String last_name) {
        // setting up frame
        setTitle("Employee Dashboard - Anudip Foundation Content Managament System");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // label for welcome statement
        JLabel welcomeLabel = new JLabel("Welcome to the Employee Dashboard, " + first_name + " " + last_name + "!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Components
        add(welcomeLabel);

        // Frame finalizing
        setVisible(true);
    }
}