package com.formation.projet.helpers;

import com.formation.projet.business.beans.Company;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 27/05/13
 * Time: 11:00
 */
public class JspHelper {

    private static SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");

    public static String title(int count) {
        StringBuilder sb = new StringBuilder();

        if (count == 0) {
            sb.append("No computers");
        } else if (count == 1) {
            sb.append("One computer");
        } else {
            sb.append(count).append(" computers");
        }

        sb.append(" found");

        return sb.toString();
    }

    /**
     * Helper generating table headers.
     *
     * @param currentOrderBy
     * @param orderBy
     * @param title
     * @return
     */
    public static String header(String filter, int currentOrderBy, int orderBy, String title) {
        StringBuilder sb = new StringBuilder();
        sb.append("<th class=\"col").append(orderBy).append(" header");
        if (Math.abs(currentOrderBy) == orderBy) {
            if (currentOrderBy < 0) {
                sb.append(" headerSortDown");
            } else {
                sb.append(" headerSortUp");
            }
        }
        sb.append("\">");

        int sort = (Math.abs(currentOrderBy) == orderBy) ? -currentOrderBy : orderBy;
        sb.append("<a href=\"").append("computers?s=").append(sort).append("&f=").append(filter).append("\">");
        sb.append(title);
        sb.append("</a>");

        sb.append("</th>");

        return sb.toString();
    }

    public static String format(Date date) {
        StringBuilder sb = new StringBuilder();
        if (date == null) {
            sb.append("<em>-</em>");
        } else {
            sb.append(format.format(date));
        }

        return sb.toString();
    }

    public static String formatCompany(Company company) {
        StringBuilder sb = new StringBuilder();
        if (company == null) {
            sb.append("<em>-</em>");
        } else {
            sb.append(company.getName());
        }

        return sb.toString();
    }
}
