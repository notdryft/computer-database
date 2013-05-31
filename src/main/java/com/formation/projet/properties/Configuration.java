package com.formation.projet.properties;


import com.formation.projet.annotations.Property;
import com.formation.projet.annotations.PropertyClass;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 29/05/13
 * Time: 11:24
 */
@PropertyClass("configuration.properties")
public class Configuration {

    private static Configuration instance;

    @Property("configuration.first_page")
    private int firstPage;

    @Property("configuration.max_items_per_page")
    private int maxItemsPerPage;

    @Property("configuration.default_sort_column")
    private int defaultSortColumn;

    @Property("configuration.default_filter")
    private String defaultFilter;

    public Configuration() {
        // Do nothing
    }

    public int getFirstPage() {
        return firstPage;
    }

    public int getMaxItemsPerPage() {
        return maxItemsPerPage;
    }

    public int getDefaultSortColumn() {
        return defaultSortColumn;
    }

    public String getDefaultFilter() {
        return defaultFilter;
    }

    public static Configuration getInstance() {
        return instance;
    }
}
