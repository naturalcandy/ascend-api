package com.ascend.ascend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String email;   
    private String password;

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
    public long getId() {
        return this.id;
    }
    
    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
