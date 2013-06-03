package com.formation.projet.application.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 31/05/13
 * Time: 17:11
 */
public class ServiceException extends RuntimeException {

    public ServiceException() {
        // Do nothing.
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
