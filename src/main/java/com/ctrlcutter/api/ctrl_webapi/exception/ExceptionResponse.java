package com.ctrlcutter.api.ctrl_webapi.exception;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExceptionResponse {

    private String message;
    private Map<String, String> data;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd' T 'hh:mm:ss")
    private Timestamp timestamp;

    public ExceptionResponse(String message, Map<String, String> data) {
        this.message = message;
        this.data = data;
        this.timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getData() {
        return this.data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
