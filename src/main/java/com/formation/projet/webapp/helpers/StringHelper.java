package com.formation.projet.webapp.helpers;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 27/05/13
 * Time: 17:19
 */
public class StringHelper {

    public static String join(List<String> strings, String delimiter) {
        if (strings == null || strings.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.size() - 1; i++) {
            sb.append(strings.get(i)).append(delimiter);
        }
        sb.append(strings.get(strings.size() - 1));

        return sb.toString();
    }

    public static String capitalize(String string) {
        if (string == null) {
            throw new NullPointerException("Argument \"string\" is null");
        } else if (string.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(string.charAt(0)).toUpperCase());

        if (string.length() > 1) {
            sb.append(string.substring(1));
        }

        return sb.toString();
    }
}
