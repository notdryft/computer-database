package com.formation.projet.exceptions;

import com.formation.projet.helpers.StringHelper;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 24/05/13
 * Time: 16:34
 */
public class PropertiesLoadingException extends RuntimeException {

    public PropertiesLoadingException(String... messages) {
        super(StringHelper.toString(messages));
    }

    public PropertiesLoadingException(Throwable cause, String... messages) {
        super(StringHelper.toString(messages), cause);
    }
}
