package com.formation.projet.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 31/05/13
 * Time: 12:36
 */
public class ParseException extends Exception {

    public ParseException() {
        super();
    }

    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseException(Throwable cause) {
        super(cause);
    }
}
