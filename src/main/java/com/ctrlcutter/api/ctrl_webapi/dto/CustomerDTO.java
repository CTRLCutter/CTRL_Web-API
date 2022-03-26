package com.ctrlcutter.api.ctrl_webapi.dto;

import java.sql.Timestamp;

public class CustomerDTO {

    private Long id;
    private String username;
    private String email;
    private Timestamp registration_date;

    public CustomerDTO(Long id, String username, String email, Timestamp registration_date) {
        this.id = id;
        this.username = username;
        this.email = email;
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

    public Timestamp getRegistration_date() {
        return this.registration_date;
    }

    public void setRegistration_date(Timestamp registration_date) {
        this.registration_date = registration_date;
    }
}
