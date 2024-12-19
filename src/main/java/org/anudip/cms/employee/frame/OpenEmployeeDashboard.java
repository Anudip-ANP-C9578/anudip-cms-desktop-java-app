
//Employee DashBoard Opening class

package org.anudip.cms.employee.frame;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class OpenEmployeeDashboard extends JFrame {

	public void openEmployeeDashboard(String first_name, String last_name) {
        // Frame Setup
        setTitle("Employee Dashboard - Anudip Foundation Content Managament System");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Welcome Label
        JLabel welcomeLabel = new JLabel("Welcome to Employee Dashboard, " + first_name + " " + last_name + "!!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Add Components
        add(welcomeLabel);

        // Finalize Frame
        setVisible(true);
    }
}
