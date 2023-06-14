package com.ascend.ascend.dto;

public class SignUpDto {
    public String email;
    public String password;
    public String firstname;
    public String lastname;


    public String getEmail() { 
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstname;
    }
    public String getLastName() {
        return lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }
    public void setLastName(String lastname) {
        this.lastname = lastname;
    }
}
