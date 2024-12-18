package org.anudip;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame extends JFrame implements ActionListener {
	// Defining UI components
	JLabel labelEmailId, labelPassword;
	JTextField tfEmailId;
	JPasswordField pfPassword;
	JButton btnSignIn, btnClear;

	// Constructor to initialize and display the SignIn Frame
	public Frame() {
		setTitle("SignIn - Anudip CMS System"); // Title of the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit app on close
		setSize(400, 300); // Setting the frame size

		JPanel panel = new JPanel(); // Panel to hold all components
		GridBagLayout layout = new GridBagLayout(); // Using GridBag layout
		panel.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();

		// Adding Email ID Label and TextField
		labelEmailId = new JLabel("Email ID");
		tfEmailId = new JTextField(10);

		// Adding Password Label and PasswordField
		labelPassword = new JLabel("Password");
		pfPassword = new JPasswordField(10);

		// Adding SignIn and Clear Buttons
		btnSignIn = new JButton("SignIn");
		btnClear = new JButton("Clear");

		// Arranging components on the panel
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(labelEmailId, gbc);

		gbc.gridx = 1;
		panel.add(tfEmailId, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(labelPassword, gbc);

		gbc.gridx = 1;
		panel.add(pfPassword, gbc);

		gbc.gridy = 2;
		panel.add(btnSignIn, gbc);

		gbc.gridy = 3;
		panel.add(btnClear, gbc);

		// Adding Action Listeners for button clicks
		btnSignIn.addActionListener(this);
		btnClear.addActionListener(this);

		// Adding the panel to the frame
		Container container = getContentPane();
		container.add(panel);
		setVisible(true); // Making the frame visible
	}

	// Handling button click events
	@Override
	public void actionPerformed(ActionEvent e) {
		EmployeeDao employeeDao = new EmployeeDao(); // Initializing DAO
		if (e.getSource() == btnSignIn) {
			String email = tfEmailId.getText(); // Getting entered email
			String password = new String(pfPassword.getPassword()); // Getting entered password

			// Validating credentials via DAO
			String employeeName = employeeDao.validateCredentials(email, password);
			if (employeeName != null) {
				// Successful login, show the Dashboard
				JOptionPane.showMessageDialog(this, "Welcome " + employeeName + "!", "Login Successful",
						JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
				new DashboardFrame(employeeName); // Launching Dashboard
			} else {
				// Failed login, show error message
				JOptionPane.showMessageDialog(this, "Invalid Access. Try Again", "Login Failed",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == btnClear) {
			// Clearing the input fields
			tfEmailId.setText("");
			pfPassword.setText("");
		}
	}
}