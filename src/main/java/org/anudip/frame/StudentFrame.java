package org.anudip.frame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.anudip.dao.StudentDao;
import org.anudip.entity.Student;

public class StudentFrame extends JFrame implements ActionListener {
	JLabel lblInformation, lblFirstName, lblLastName, lblDOB, lblGender;
	JTextField tfFirstName, tfLastName, tfDOB, tfGender;
	JButton btnAdd, btnClear;

	public StudentFrame() {
		setTitle("Add New Student - Anudip Foundation Content Management System"); // Title name of frame
		setSize(400, 300); // size of the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel(); // creating Panel

		GridBagLayout gridBagLayout = new GridBagLayout();
		panel.setLayout(gridBagLayout);

		GridBagConstraints gridBagConstraints = new GridBagConstraints();

		lblInformation = new JLabel("Student Personal Information");
		lblFirstName = new JLabel("First Name");
		lblLastName = new JLabel("Last Name");
		lblDOB = new JLabel("Date Of Birth");
		lblGender = new JLabel("Gender");

		tfFirstName = new JTextField(10);
		tfLastName = new JTextField(10);
		tfDOB = new JTextField(10);
		tfGender = new JTextField(10);

		btnAdd = new JButton("Add");
		btnClear = new JButton("Clear");

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		panel.add(lblInformation, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		panel.add(lblFirstName, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		panel.add(lblLastName, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		panel.add(lblDOB, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		panel.add(lblGender, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		panel.add(tfFirstName, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		panel.add(tfLastName, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		panel.add(tfDOB, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		panel.add(tfGender, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 5;
		panel.add(btnAdd, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 6;
		panel.add(btnClear, gridBagConstraints);
		btnAdd.addActionListener(this);
		btnClear.addActionListener(this);

		getContentPane().add(panel);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StudentDao studentDao = new StudentDao();
		if (e.getSource() == btnAdd) {

			// Create Student object
			Student student = new Student();
			student.setFirstName(tfFirstName.getText());
			student.setLastName(tfLastName.getText());
			student.setDateOfBirth(tfDOB.getText());
			student.setGender(tfGender.getText());

			// Call DAO to insert student into database
			boolean success = studentDao.insertStudent(student);

			// Show success or error message
			if (success) {
				JOptionPane.showMessageDialog(null, "Student Added Successfully");
			} else {
				JOptionPane.showMessageDialog(null, "Error in adding student data");
			}
		}

		if (e.getSource() == btnClear) {
			tfFirstName.setText("");
			tfLastName.setText("");
			tfDOB.setText("");
			tfGender.setText("");
		}
	}
}