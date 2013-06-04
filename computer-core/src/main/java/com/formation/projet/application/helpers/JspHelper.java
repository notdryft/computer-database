package com.formation.projet.application.helpers;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 27/05/13
 * Time: 11:00
 */
public class JspHelper {

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
}
