package com.formation.projet.helpers;

import com.formation.projet.exceptions.PropertiesLoadingException;

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

    public static int parseProperty(Properties properties, String name) {
        int value;
        try {
            value = Integer.parseInt(properties.getProperty(name));
        } catch (NumberFormatException e) {
            throw new PropertiesLoadingException(e, "Property \"", name, "\" is not an int");
        }

        return value;
    }
}
