package com.formation.projet.business.forms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 28/05/13
 * Time: 15:09
 */
public class FormElement {

    private String name;

    private String value;

    private Object valueObject;

    private boolean valid;

    private List<String> messages;

    public FormElement(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Argument \"name\" should not be null");
        }

        this.name = name;
        this.valid = true;
        this.messages = new ArrayList<String>();
    }

    public FormElement(String name, String message) {
        this(name);

        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("Argument \"message\" should not be null or empty");
        }

        this.messages.add(message);
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Object getValueObject() {
        return valueObject;
    }

    public boolean isValid() {
        return valid;
    }

    public List<String> getMessages() {
        return new ArrayList<String>(messages);
    }

    public void validate(Object valueObject) {
        this.valid = true;
        this.valueObject = valueObject;
    }

    public void invalidate() {
        this.valid = false;
    }

    public void invalidate(String message) {
        this.valid = false;

        if (message != null && !message.isEmpty()) {
            this.messages.add(message);
        }
    }
}
