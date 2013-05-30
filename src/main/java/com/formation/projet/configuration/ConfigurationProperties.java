package com.formation.projet.configuration;

import com.formation.projet.helpers.IntHelper;
import com.formation.projet.util.PropertiesUtils;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 29/05/13
 * Time: 11:24
 */
public enum ConfigurationProperties {
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

    private ConfigurationProperties() {
        Properties properties =
                PropertiesUtils.loadProperties(
                        PATH,
                        FIRST_PAGE, MAX_ITEMS_PER_PAGE, DEFAULT_SORT_COLUMN, DEFAULT_FILTER);

        this.firstPage = IntHelper.parseProperty(properties, FIRST_PAGE);
        this.maxItemsPerPage = IntHelper.parseProperty(properties, MAX_ITEMS_PER_PAGE);
        this.defaultSortColumn = IntHelper.parseProperty(properties, DEFAULT_SORT_COLUMN);
        this.defaultFilter = properties.getProperty(DEFAULT_FILTER);
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
