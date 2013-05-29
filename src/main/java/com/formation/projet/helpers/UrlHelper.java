package com.formation.projet.helpers;

import com.formation.projet.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 29/05/13
 * Time: 11:23
 */
public class UrlHelper {

    public static String linkSort(int sortColumn, String filter) {
        return linkAttributes(Configuration.FIRST_PAGE, sortColumn, filter);
    }

    public static String linkAttributes(int page, int sortColumn, String filter) {
        List<String> attributes = new ArrayList<String>();

        if (page > Configuration.FIRST_PAGE) {
            attributes.add("p=" + page);
        }

        if (sortColumn != Configuration.DEFAULT_SORT_COLUMN) {
            attributes.add("s=" + sortColumn);
        }

        if (!filter.equals(Configuration.DEFAULT_FILTER)) {
            attributes.add("f=" + filter);
        }

        if (attributes.isEmpty()) {
            return "";
        }

        return "?" + StringHelper.join(attributes, "&");
    }
}
