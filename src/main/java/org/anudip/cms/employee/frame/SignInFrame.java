package org.anudip.cms.employee.frame;

//import employee dashboard;

import org.anudip.cms.employee.dao.EmployeeDAO;
import org.anudip.cms.employee.entity.Employee;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignInFrame extends JFrame /* implements ActionListener */ {
	public SignInFrame() {

		setTitle("Sign In - Anudip Foundation Content Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setLocationRelativeTo(null);
		setSize(700, 600);

		JPanel jPanel = new JPanel();

		GridBagLayout gridBagLayout = new GridBagLayout();
		jPanel.setLayout(gridBagLayout);

		GridBagConstraints gridBagConstraints = new GridBagConstraints();

		JLabel greetingLabel = new JLabel("");
		JLabel lblEmailAddress = new JLabel("EmailAddress: ");
		JLabel lblPassword = new JLabel("Password: ");

		JTextField tfEmailAddress = new JTextField(20);

		JPasswordField pfPassword = new JPasswordField(20);

		JButton btnSignIn = new JButton("Sign In");
		JButton btnClear = new JButton("Clear");

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		jPanel.add(greetingLabel, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		jPanel.add(lblEmailAddress, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		jPanel.add(tfEmailAddress, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		jPanel.add(lblPassword, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		jPanel.add(pfPassword, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		jPanel.add(btnSignIn, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		jPanel.add(btnClear, gridBagConstraints);

		Container container = getContentPane();
		container.add(jPanel);

		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfEmailAddress.setText("");
				pfPassword.setText("");
			}
		});

		btnSignIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				String email = tfEmailAddress.getText();
				String password = new String(pfPassword.getPassword());

				try {
					EmployeeDAO dao = new EmployeeDAO();
					Employee employee = dao.verifyCredentials(email, password);

					if (employee != null) {
						JOptionPane.showMessageDialog(null,"Welcome " + employee.getFirstName() + " " + employee.getLastName());
						
						OpenEmployeeDashboard openEmployeeDashboard = new OpenEmployeeDashboard();
						openEmployeeDashboard.openEmployeeDashboard(employee.getFirstName(), employee.getLastName());

					} else {
						JOptionPane.showMessageDialog(null, "Invalid Access. Please try Again");
					}
					dao.closeConnection();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		});
		setVisible(true);
	}
}