package com.ascend.ascend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String email;   

    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String phoneNumber;

    @Column
    private String birthday;

    @Column
    private String plaidAccessToken;

    public User() {}

    // constructor
    public User(String email, String firstName, String lastName, String password,
                String phoneNumber, String birthday) {
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String toString() {
        return String.format("User[id=%d, email='%s', password='%s']", id, email, password);
    }
    
    public Long getId() { return this.id; }
    
    public String getEmail() { return this.email; }

    public String getPassword() { return this.password; }

    public String getFirstName() { return this.firstName; }

    public String getLastName() { return this.lastName; }

    public String getPhoneNumber() {return this.phoneNumber;}

    public String getBirthday() { return this.birthday; }

    public String getPlaidAccessToken () { return this.plaidAccessToken; }

    public void setEmail(String email) {this.email = email;}

    public void setPassword(String password) {this.password = password;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public void setLastName(String lastName) {this.lastName= lastName;}

    public void setBirthday(String birthday) {this.birthday = birthday;}

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public void setPlaidAccessToken(String accessToken) {this.plaidAccessToken = accessToken;}
}
