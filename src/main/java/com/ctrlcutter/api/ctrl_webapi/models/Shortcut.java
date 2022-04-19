package com.ctrlcutter.api.ctrl_webapi.models;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Shortcuts")
public class Shortcut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shortcut_id")
    private Long id;

    private String command;

    private String keyboardKey;

    @ElementCollection
    private List<String> modifierKeys;

    @ElementCollection
    private List<String> parameters;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @JsonIgnoreProperties("shortcuts")
    @JsonIgnore
    private Customer customer;

    public Shortcut() {}

    public Shortcut(String command, String keyboardKey, List<String> modifierKeys, List<String> parameters, Customer customer) {
        this.command = command;
        this.keyboardKey = keyboardKey;
        this.modifierKeys = modifierKeys;
        this.parameters = parameters;
        this.customer = customer;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getKeyboardKey() {
        return this.keyboardKey;
    }

    public void setKeyboardKey(String key) {
        this.keyboardKey = key;
    }

    public List<String> getModifierKeys() {
        return this.modifierKeys;
    }

    public void setModifierKeys(List<String> modifierKeys) {
        this.modifierKeys = modifierKeys;
    }

    public List<String> getParameters() {
        return this.parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
