package com.formation.projet.core.helpers;

import com.formation.projet.core.exceptions.ParseException;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 27/05/13
 * Time: 14:33
 */
public class IntHelper {

    static int parseInt(String string, int minValue) {
        if (string == null) {
            return minValue;
        }

        int value;
        try {
            value = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            value = minValue;
        }

        return value;
    }

    public static int parseProperty(Properties properties, String name) throws ParseException {
        int value;
        try {
            value = Integer.parseInt(properties.getProperty(name));
        } catch (NumberFormatException e) {
            throw new ParseException(String.format("Property \"%s\" is not an int", name), e);
        }

        return value;
    }
}
