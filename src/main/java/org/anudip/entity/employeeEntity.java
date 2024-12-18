package org.anudip.entity;

public class employeeEntity {
    private int id;
    private String first_name;
    private String last_name;
    private String email_address;
    private String password;

    // Constructor
    public employeeEntity() {}

    public employeeEntity(int id, String first_name, String last_name, String email_address, String password) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email_address = email_address;
        this.password = password;
    }

    // Getters and Setters
    public int getid() {
        return id;
    }

    public void setEmpId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getEmailId() {
        return email_address;
    }

    public void setEmailId(String email_address) {
        this.email_address = email_address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "employeeEntity [id=" + id + ", firstName=" + first_name + ", lastName=" + last_name + 
               ", emailId=" + email_address + "]";
    }
}

