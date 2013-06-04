package com.formation.projet.webapp.helpers;

import com.formation.projet.core.properties.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 30/05/13
 * Time: 16:40
 */
public class PageHelper {

    private static final Configuration configuration = Configuration.getInstance();

    public static int parsePage(String pageString) {
        int page = IntHelper.parseInt(pageString, configuration.getFirstPage());
        if (page < configuration.getFirstPage()) {
            page = configuration.getFirstPage();
        }

        return page;
    }

    public static int parseSortColumn(String sortColumnString) {
        return IntHelper.parseInt(sortColumnString, configuration.getDefaultSortColumn());
    }

    public static String parseFilter(String filterString) {
        if (filterString == null) {
            return configuration.getDefaultFilter();
        }

        return filterString;
    }
}
