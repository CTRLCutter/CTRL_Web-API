package com.ctrlcutter.api.ctrl_webapi.helper;

import org.springframework.lang.NonNull;

public class LoginForm {
    @NonNull
    private String email;
    @NonNull
    private String password;

    public LoginForm(String email, String password) {
        this.email = email;
        this.password = password;
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
}
