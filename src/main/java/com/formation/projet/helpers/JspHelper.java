package com.formation.projet.helpers;

import com.formation.projet.business.beans.Company;

import java.util.List;

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

    private static String twitterBootstrapInput(String name, String label, String input, boolean hasErrors, List<String> help) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"clearfix");
        if (hasErrors) {
            sb.append(" error");
        }
        sb.append("\">");

        sb.append("<label for=\"").append(name).append("\">").append(label).append("</label>");

        sb.append("<div class=\"input\">");
        sb.append(input);
        sb.append("<span class=\"help-inline\">").append(StringHelper.join(help, ", ")).append("</span>");
        sb.append("</div>");

        sb.append("</div>");

        return sb.toString();
    }

    public static String inputText(String name, String label, boolean hasErrors, List<String> help) {
        StringBuilder input = new StringBuilder();
        input.append("<input type=\"text\" id=\"").append(name).append("\" name=\"").append(name).append("\">");

        return twitterBootstrapInput(name, label, input.toString(), hasErrors, help);
    }

    public static String select(String name, String label, String defaultValue, List<Company> companies, boolean hasErrors, List<String> help) {
        StringBuilder input = new StringBuilder();
        input.append("<select id=\"").append(name).append("\" name=\"").append(name).append("\">");
        input.append("<option class=\"blank\" value=\"\">").append(defaultValue).append("</option>");
        if (companies != null && !companies.isEmpty()) {
            for (Company company : companies) {
                input.append("<option value=\"").append(company.getId()).append("\">").append(company.getName()).append("</option>");
            }
        }
        input.append("</select>");

        return twitterBootstrapInput(name, label, input.toString(), hasErrors, help);
    }
}
