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

    private static int parse(String string, int minValue) {
        int value;
        if (string == null) {
            value = minValue;
        } else {
            try {
                value = Integer.parseInt(string);
            } catch (NumberFormatException e) {
                value = minValue;
            }
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

    public static int parsePage(String pageString, int firstPage) {
        int page = parse(pageString, firstPage);
        if (page < firstPage) {
            page = firstPage;
        }

        return page;
    }

    public static int parseSortColumn(String sortColumnString, int defaultSortColumn) {
        return parse(sortColumnString, defaultSortColumn);
    }
}
