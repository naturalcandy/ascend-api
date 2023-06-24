package com.ascend.ascend.dto;

public class SignUpDto {
    public String email;
    public String password;
    public String firstName;
    public String lastName;
    public String birthday;
    public String phoneNumber;


    public String getEmail() { 
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName= firstName;
    }
    public void setLastName(String lastName) {
        this.lastName= lastName;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
