package com.formation.projet.helpers;

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
        int page = parse(pageString, 0);
        if (page < 0) {
            page = 0;
        }

        return page;
    }

    public static int parseSortColumn(String sortColumnString) {
        int sortColumn = parse(sortColumnString, 2);

        return sortColumn;
    }
}
