package org.anudip.frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EmployeeDashboardFrame extends JFrame {
	JLabel lblWelcome;
    
	// constructor
	public EmployeeDashboardFrame(String employeeName) {
		setTitle("Employee Dashboard");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();

		lblWelcome = new JLabel("Welcome to the Employee Dashboard, " + employeeName);

		panel.add(lblWelcome);
		
		getContentPane().add(panel);
		setVisible(true);
	}
}