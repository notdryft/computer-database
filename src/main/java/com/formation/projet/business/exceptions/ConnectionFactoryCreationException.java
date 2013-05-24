package com.formation.projet.business.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 24/05/13
 * Time: 16:21
 */
public class ConnectionFactoryCreationException extends Exception {

    public ConnectionFactoryCreationException() {
        super();
    }

    public ConnectionFactoryCreationException(String message) {
        super(message);
    }

    public ConnectionFactoryCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionFactoryCreationException(Throwable cause) {
        super(cause);
    }
}
