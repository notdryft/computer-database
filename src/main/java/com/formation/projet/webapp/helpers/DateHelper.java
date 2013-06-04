package com.formation.projet.webapp.helpers;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 04/06/13
 * Time: 09:29
 */
public class DateHelper {

    public static java.sql.Date toSQLDate(java.util.Date date) {
        if (date == null) {
            return null;
        }

        return new java.sql.Date(date.getTime());
    }

    public static java.util.Date toDate(java.sql.Date date) {
        if (date == null) {
            return null;
        }

        return new java.util.Date(date.getTime());
    }
}
