package org.anudip;

//entity class
public class Employee {
	private int employeeID;
	private String firstName;
	private String lastName;
	private String emailID;
	private String password;

	// default constructor
	public Employee() {
	}

	// constructor with parameters
	public Employee(int employeeID, String firstName, String lastName, String emailID, String password) {
		this.employeeID = employeeID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailID = emailID;
		this.password = password;
	}

	// Getters and Setters
	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailID=" + emailID + ", password=" + password + "]";
	}
}