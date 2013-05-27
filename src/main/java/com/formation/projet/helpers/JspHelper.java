package com.formation.projet.helpers;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 27/05/13
 * Time: 11:00
 */
public class JspHelper {

    /**
     * Helper generating table headers.
     *
     * @param currentOrderBy
     * @param orderBy
     * @param title
     * @return
     */
    public static String header(int currentOrderBy, int orderBy, String title) {
        StringBuilder sb = new StringBuilder();
        sb.append("<th class=\"col").append(" header");
        if (Math.abs(currentOrderBy) == orderBy) {
            if (currentOrderBy < 0) {
                sb.append(" headerSortDown");
            } else {
                sb.append(" headerSortUp");
            }
        }
        sb.append("\">");

        sb.append("<a href=\"").append("#").append("\">"); // "@link(0, Some(orderBy))"
        sb.append(title);
        sb.append("</a>");

        sb.append("</th>");

        return sb.toString();
    }
}
