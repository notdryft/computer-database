package com.formation.projet.helpers;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 27/05/13
 * Time: 14:33
 */
public class IntHelper {

    public static int parsePage(String pageString) {
        int page;
        if (pageString == null) {
            page = 0;
        } else {
            try {
                page = Integer.parseInt(pageString);
                if (page < 0) {
                    page = 0;
                }
            } catch (NumberFormatException e) {
                page = 0;
            }
        }

        return page;
    }
}
