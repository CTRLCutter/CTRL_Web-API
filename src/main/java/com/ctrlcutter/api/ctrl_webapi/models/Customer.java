package com.ctrlcutter.api.ctrl_webapi.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd' T 'hh:mm:ss")
    private Timestamp registration_date;

    @OneToOne(mappedBy = "customer")
    private Session session;

    public Customer() {
    }

    public Customer(String username, String email, String password, Timestamp registration_date) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.registration_date = registration_date;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getRegistration_date() {
        return this.registration_date;
    }

    public void setRegistration_date(Timestamp registration_date) {
        this.registration_date = registration_date;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + this.id + ", username='" + this.username + "', email='" + this.email + "', registration_date='" + this.registration_date + "'}";
    }
}
