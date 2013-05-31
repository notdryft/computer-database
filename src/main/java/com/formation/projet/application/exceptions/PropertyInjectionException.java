package com.formation.projet.application.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 31/05/13
 * Time: 12:33
 */
public class PropertyInjectionException extends RuntimeException {

    public PropertyInjectionException() {
        super();
    }

    public PropertyInjectionException(String message) {
        super(message);
    }

    public PropertyInjectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertyInjectionException(Throwable cause) {
        super(cause);
    }
}
