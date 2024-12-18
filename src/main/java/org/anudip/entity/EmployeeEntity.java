package org.anudip.entity;

public class EmployeeEntity {
    private int id;
    private String first_name;
    private String last_name;
    private String email_id;
    private String password;

    public EmployeeEntity(int id, String first_name, String last_name, String email_id, String password) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email_id = email_id;
        this.password = password;
    }

    // Getters
    public int getid() {
        return id;
    }

    public String getfirst_name() {
        return first_name;
    }

    public String getlast_name() {
        return last_name;
    }

    public String getemail_id() {
        return email_id;
    }

    public String getPassword() {
        return password;
    }
}

