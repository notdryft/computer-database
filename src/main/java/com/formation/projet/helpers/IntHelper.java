package com.formation.projet.helpers;

import com.formation.projet.configuration.Configuration;

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

    public static int parsePage(String pageString) {
        int page = parse(pageString, Configuration.FIRST_PAGE);
        if (page < Configuration.FIRST_PAGE) {
            page = Configuration.FIRST_PAGE;
        }

        return page;
    }

    public static int parseSortColumn(String sortColumnString) {
        return parse(sortColumnString, Configuration.DEFAULT_SORT_COLUMN);
    }
}
