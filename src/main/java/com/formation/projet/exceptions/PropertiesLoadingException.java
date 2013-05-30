package com.formation.projet.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 24/05/13
 * Time: 16:34
 */
public class PropertiesLoadingException extends RuntimeException {

    public PropertiesLoadingException() {
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

    public PropertiesLoadingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
