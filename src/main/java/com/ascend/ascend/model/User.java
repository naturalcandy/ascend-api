package com.ascend.ascend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    @Basic(optional = false)
    @Column(nullable = false)
    private String email;   

    @Basic(optional = false)
    @Column(nullable = true)
    private String password;

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
