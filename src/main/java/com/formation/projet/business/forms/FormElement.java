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

    public FormElement() {
        valid = true;
        messages = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setValueObject(Object valueObject) {
        this.valueObject = valueObject;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }
}
