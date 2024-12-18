package org.anudip;

// Represents an Employee entity
public class Employee {
	private int emp_Id; // Unique employee ID
	private String first_Name; // First name of the employee
	private String last_Name; // Last name of the employee
	private String email_Id; // Employee's email
	private String password; // Password for authentication

	// Default constructor
	public Employee() {
	}

	// Parameterized constructor
	public Employee(int emp_Id, String first_Name, String last_Name, String email_Id, String password) {
		this.emp_Id = emp_Id;
		this.first_Name = first_Name;
		this.last_Name = last_Name;
		this.email_Id = email_Id;
		this.password = password;
	}

	// Getters and Setters for all properties
	public int getEmp_Id() {
		return emp_Id;
	}

	public void setEmp_Id(int emp_Id) {
		this.emp_Id = emp_Id;
	}

	public String getFirst_Name() {
		return first_Name;
	}

	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}

	public String getLast_Name() {
		return last_Name;
	}

	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}

	public String getEmail_Id() {
		return email_Id;
	}

	public void setEmail_id(String email_Id) {
		this.email_Id = email_Id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// Overriding toString() for better object representation
	@Override
	public String toString() {
		return "Employee [emp_Id=" + emp_Id + ", first_Name=" + first_Name + ", last_Name=" + last_Name + ", email_Id="
				+ email_Id + ", password=" + password + "]";
	}
}
