package org.anudip.frame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.anudip.dao.StudentDao;

public class EmployeeDashboardFrame extends JFrame implements ActionListener {
	JLabel lblWelcome, lblStudent, lblBranch, lblCourse;

	JButton btnAddStudent, btnAddBranch, btnAddCourse;
	JButton btnEditStudent, btnEditBranch, btnEditCourse;
	JButton btnRemoveStudent, btnRemoveBranch, btnRemoveCourse;
	JButton btnSignOut;

	// constructor
	public EmployeeDashboardFrame(String employeeName) {
		setTitle("Employee Dashboard - Anudip Foundation Content Management System");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();

		GridBagLayout gridBagLayout = new GridBagLayout();
		panel.setLayout(gridBagLayout);

		GridBagConstraints gridBagConstraints = new GridBagConstraints();

		lblWelcome = new JLabel("Welcome to the Employee Dashboard, " + employeeName);

		lblStudent = new JLabel("Student Operations");
		lblBranch = new JLabel("Branch Operations");
		lblCourse = new JLabel("Course Operations");

		btnAddStudent = new JButton("Add Student");
		btnAddBranch = new JButton("Add Branch");
		btnAddCourse = new JButton("Add Course");

		btnEditStudent = new JButton("Edit Student");
		btnEditBranch = new JButton("Edit Branch");
		btnEditCourse = new JButton("Edit Course");

		btnRemoveStudent = new JButton("Remove Student");
		btnRemoveBranch = new JButton("Remove Branch");
		btnRemoveCourse = new JButton("Remove Course");

		btnSignOut = new JButton("Sign Out");

		// welcome label
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 3;
		panel.add(lblWelcome, gridBagConstraints);

		// Label Student operations and 3 buttons
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 1;
		panel.add(lblStudent, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		panel.add(btnAddStudent, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		panel.add(btnEditStudent, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		panel.add(btnRemoveStudent, gridBagConstraints);

		// Label 2 Branch operations and 3 buttons
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		panel.add(lblBranch, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		panel.add(btnAddBranch, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		panel.add(btnEditBranch, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		panel.add(btnRemoveBranch, gridBagConstraints);

		// Label 3 Course operations and 3 buttons
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 1;
		panel.add(lblCourse, gridBagConstraints);

		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 2;
		panel.add(btnAddCourse, gridBagConstraints);

		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 3;
		panel.add(btnEditCourse, gridBagConstraints);

		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 4;
		panel.add(btnRemoveCourse, gridBagConstraints);

		// Sign out button
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.gridwidth = 3;
		panel.add(btnSignOut, gridBagConstraints);

		btnAddStudent.addActionListener(this);
		btnEditStudent.addActionListener(this);
		btnRemoveStudent.addActionListener(this);

		btnAddBranch.addActionListener(this);
		btnEditBranch.addActionListener(this);
		btnRemoveBranch.addActionListener(this);

		btnAddCourse.addActionListener(this);
		btnEditCourse.addActionListener(this);
		btnRemoveCourse.addActionListener(this);

		btnSignOut.addActionListener(this);

		getContentPane().add(panel);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		new StudentFrame();
	}
}