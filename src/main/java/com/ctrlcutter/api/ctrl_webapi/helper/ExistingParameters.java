package com.ctrlcutter.api.ctrl_webapi.helper;

import java.util.HashMap;
import java.util.Map;

public class ExistingParameters {
    private boolean existing;
    private Map<String, String> parameters;

    public ExistingParameters() {
        this.existing = false;
        this.parameters = new HashMap<>();
    }

    public boolean areExisting() {
        return this.existing;
    }

    public void setExisting() {
        this.existing = true;
    }

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public void addParameter(String key, String value) {
        this.parameters.put(key, value);
    }
}
