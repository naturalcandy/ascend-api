package com.ascend.ascend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private long id;

    @Column
    private String email;   

    @Column
    private String password;

    @Column
    private String firstName;

    @Basic(optional = true)
    @Column(nullable = true)
    private String lastname;

    public User() {}

    // constructor
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // toString
    public String toString() {
        return String.format("User[id=%d, email='%s', password='%s']", id, email, password);
    }
    
    // getters and setters
    public long getId() { return this.id; }
    
    public String getEmail() { return this.email; }

    public String getPassword() {
        return this.password;
    }
    
    public String getFirstName() {
        return this.firstname;
    }
    public String getLastName() {
        return this.lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public void setLastName(String lastName) {this.lastName= lastName;}

    public void setBirthday(String birthday) {this.birthday = birthday;}

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
}
