package com.ctrlcutter.api.ctrl_webapi.models;

import java.sql.Timestamp;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long session_id;

    @Column(nullable = false, unique = true)
    private String session_key;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @JsonIgnoreProperties("session")
    private Customer customer;

    @DateTimeFormat(pattern = "yyyy-MM-dd' T 'hh:mm:ss")
    private Timestamp creation_time;

    @DateTimeFormat(pattern = "yyyy-MM-dd' T 'hh:mm:ss")
    private Timestamp valid_until;

    public Session() {}

    public Session(String session_key, Customer customer, Timestamp creation_time, Timestamp valid_until) {
        this.session_key = session_key;
        this.customer = customer;
        this.creation_time = creation_time;
        this.valid_until = valid_until;
    }

    public Long getSession_id() {
        return this.session_id;
    }

    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }

    public String getSession_key() {
        return this.session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Timestamp getCreation_time() {
        return this.creation_time;
    }

    public void setCreation_time(Timestamp creation_time) {
        this.creation_time = creation_time;
    }

    public Timestamp getValid_until() {
        return this.valid_until;
    }

    public void setValid_until(Timestamp valid_until) {
        this.valid_until = valid_until;
    }

    @Override
    public String toString() {
        return "Session{" + "session_id=" + this.session_id + ", session_key='" + this.session_key + '\'' + ", customer=" + this.customer + ", creation_time="
                + this.creation_time + ", valid_until=" + this.valid_until + '}';
    }
}
