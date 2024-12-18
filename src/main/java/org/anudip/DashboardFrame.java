package org.anudip;

import javax.swing.*;

// Represents the Employee Dashboard after login
public class DashboardFrame extends JFrame {
	public DashboardFrame(String employeeName) {
		setTitle("Employee Dashboard"); // Title of the frame
		setSize(400, 200); // Setting the frame size
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit app on close

		// Displaying a welcome message
		JLabel welcomeLabel = new JLabel("Welcome to the Employee Dashboard, " + employeeName + "!");
		add(welcomeLabel);

		setVisible(true); // Making the frame visible
	}
}