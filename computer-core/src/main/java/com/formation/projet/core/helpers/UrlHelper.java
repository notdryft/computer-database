package com.formation.projet.core.helpers;

import com.formation.projet.core.properties.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 29/05/13
 * Time: 11:23
 */
public class UrlHelper {

    private static final Configuration configuration = Configuration.getInstance();

    public static String linkSort(int sortColumn, String filter) {
        return linkAttributes(configuration.getFirstPage(), sortColumn, filter);
    }

    public static String linkAttributes(int page, int sortColumn, String filter) {
        List<String> attributes = new ArrayList<String>();

        if (page > configuration.getFirstPage()) {
            attributes.add("p=" + page);
        }

        if (sortColumn != configuration.getDefaultSortColumn()) {
            attributes.add("s=" + sortColumn);
        }

        if (!filter.equals(configuration.getDefaultFilter())) {
            attributes.add("f=" + filter);
        }

        if (attributes.isEmpty()) {
            return "";
        }

        return "?" + StringHelper.join(attributes, "&");
    }
}
