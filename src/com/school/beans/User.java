package com.school.beans;

public class User {

    protected int id;
    protected String firstname;
    protected String lastname;
    protected String email;
    protected String username;
    protected String password;
    protected String status = null;

    public User() {
    }

    public User(int id, String fname, String lname, String email, String uname, String pword) {
        this.id = id;
        this.firstname = fname;
        this.lastname = lname;
        this.email = email;
        this.username = uname;
        this.password = pword;
    }

    public User(String fname, String lname, String email, String uname, String pword) {
        this.id = id;
        this.firstname = fname;
        this.lastname = lname;
        this.email = email;
        this.username = uname;
        this.password = pword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
