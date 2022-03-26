package com.ctrlcutter.api.ctrl_webapi.dto;

public class SessionDTO {

    private String sessionKey;

    public SessionDTO() {}

    public SessionDTO(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getSessionKey() {
        return this.sessionKey;
    }
}
