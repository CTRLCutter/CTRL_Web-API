package com.ctrlcutter.api.ctrl_webapi.dto;

public class AnonymizedScriptDTO {

    private String command;
    private String key;
    private String[] modifierKeys;
    private String[] parameters;

    public AnonymizedScriptDTO() {}

    public AnonymizedScriptDTO(String command, String key, String[] modifierKeys, String[] parameters) {
        this.command = command;
        this.key = key;
        this.modifierKeys = modifierKeys;
        this.parameters = parameters;
    }

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String[] getModifierKeys() {
        return this.modifierKeys;
    }

    public void setModifierKeys(String[] modifierKeys) {
        this.modifierKeys = modifierKeys;
    }

    public String[] getParameters() {
        return this.parameters;
    }

    public void setParameters(String[] parameters) {
        this.parameters = parameters;
    }
}
