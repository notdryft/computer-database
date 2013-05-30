package com.formation.projet.properties;

import com.formation.projet.util.Properties;


/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 29/05/13
 * Time: 11:24
 */
public enum Configuration {
    instance;

    private static final String PATH = "configuration.properties";

    private static final String FIRST_PAGE = "configuration.first_page";

    private static final String MAX_ITEMS_PER_PAGE = "configuration.max_items_per_page";

    private static final String DEFAULT_SORT_COLUMN = "configuration.default_sort_column";

    private static final String DEFAULT_FILTER = "configuration.default_filter";

    private int firstPage;

    private int maxItemsPerPage;

    private int defaultSortColumn;

    private String defaultFilter;

    private Configuration() {
        Properties properties =
                Properties.loadProperties(
                        PATH,
                        FIRST_PAGE, MAX_ITEMS_PER_PAGE, DEFAULT_SORT_COLUMN, DEFAULT_FILTER);

        this.firstPage = properties.getInt(FIRST_PAGE);
        this.maxItemsPerPage = properties.getInt(MAX_ITEMS_PER_PAGE);
        this.defaultSortColumn = properties.getInt(DEFAULT_SORT_COLUMN);
        this.defaultFilter = properties.getString(DEFAULT_FILTER);
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
}
