package com.school.beans;

public class Intern extends User {

    public Intern(String fname, String lname, String email, String uname, String pword) {
        super(fname, lname, email, uname, pword);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus() {
        this.status = "IN";
    }
}
