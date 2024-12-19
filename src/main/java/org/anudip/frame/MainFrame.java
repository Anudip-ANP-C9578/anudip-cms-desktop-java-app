package org.anudip.frame;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.anudip.dao.EmployeeDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.*;

public class MainFrame extends JFrame implements ActionListener {
	JLabel lblEmailId, lblPassword;
	JTextField tfEmailId;
	JPasswordField pfPassword;
	JButton btnSignIn, btnClear;

	public MainFrame() {
		setTitle("SignIn - Anudip CMS System"); // Title name of frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300); // size of the frame

		JPanel panel = new JPanel(); // creating Panel

		GridBagLayout gridBagLayout = new GridBagLayout();
		panel.setLayout(gridBagLayout);

		GridBagConstraints gridBagConstraints = new GridBagConstraints();

		lblEmailId = new JLabel("Email ID");
		tfEmailId = new JTextField(10);

		lblPassword = new JLabel("Password");
		pfPassword = new JPasswordField(10);

		btnSignIn = new JButton("SignIn");
		btnClear = new JButton("Clear");

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		panel.add(lblEmailId, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		panel.add(tfEmailId, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		panel.add(lblPassword, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		panel.add(pfPassword, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		panel.add(btnSignIn, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		panel.add(btnClear, gridBagConstraints);

		btnSignIn.addActionListener(this);
		btnClear.addActionListener(this);

		Container container = getContentPane();
		container.add(panel);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		EmployeeDao employeeDao = new EmployeeDao();
		if (e.getSource() == btnSignIn) {
			String email = tfEmailId.getText();
			String password = new String(pfPassword.getPassword());

			// Validate user credentials using DAO
			String employeeName = employeeDao.validateCredentials(email, password);
			if (employeeName != null) {
				// Fetch employee full name

				JOptionPane.showMessageDialog(this, "Welcome " + employeeName + "!", "LogIn Successful",
						JOptionPane.INFORMATION_MESSAGE);
				this.dispose();

				// Open the employee dashboard after successful login
				new EmployeeDashboardFrame(employeeName);
			} else {
				JOptionPane.showMessageDialog(this, "Invalid Access. Try Again", "LogIn Failed",
						JOptionPane.ERROR_MESSAGE);
			}

			btnClear.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					tfEmailId.setText("");
					pfPassword.setText("");
				}
			});
		}
	}
}