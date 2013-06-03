package com.formation.projet.application.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 31/05/13
 * Time: 17:19
 */
public class ConnectionException extends RuntimeException {

    public ConnectionException() {
        // Do nothing.
    }

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionException(Throwable cause) {
        super(cause);
    }
}
