package com.formation.projet.helpers;

import com.formation.projet.properties.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 30/05/13
 * Time: 16:40
 */
public class PageHelper {

    private static Configuration configuration = Configuration.instance;

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
