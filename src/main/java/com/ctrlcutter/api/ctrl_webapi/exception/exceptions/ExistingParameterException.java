package com.ctrlcutter.api.ctrl_webapi.exception.exceptions;

import java.util.Map;

public class ExistingParameterException extends RuntimeException {

    private Map<String, String> parameters;

    public ExistingParameterException(String message, Map<String, String> parameters) {
        super(message);
        this.parameters = parameters;
    }

    public Map<String, String> getParameters() {
        return this.parameters;
    }
}
