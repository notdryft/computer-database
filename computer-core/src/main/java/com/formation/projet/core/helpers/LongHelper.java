package com.formation.projet.core.helpers;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 28/05/13
 * Time: 17:24
 */
public class LongHelper {

    private static long parse(String string, long minValue) {
        long value;
        if (string == null) {
            value = minValue;
        } else {
            try {
                value = Long.parseLong(string);
            } catch (NumberFormatException e) {
                value = minValue;
            }
        }

        return value;
    }

    public static long parseId(String idString) {
        return parse(idString, -1);
    }
}
