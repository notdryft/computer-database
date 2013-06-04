package com.formation.projet.core.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 24/05/13
 * Time: 16:34
 */
public class PropertiesLoadingException extends Exception {

    public PropertiesLoadingException() {
        // Do nothing.
    }

    public PropertiesLoadingException(String message) {
        super(message);
    }

    public PropertiesLoadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertiesLoadingException(Throwable cause) {
        super(cause);
    }
}
