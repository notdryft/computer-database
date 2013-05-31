package com.formation.projet.properties;


import com.formation.projet.annotations.Property;
import com.formation.projet.annotations.PropertyClass;
import com.formation.projet.annotations.PropertyType;
import com.formation.projet.annotations.Types;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 29/05/13
 * Time: 11:24
 */
@PropertyClass("configuration.properties")
public class Configuration {

    private static Configuration instance = null;

    @PropertyType(Types.INT)
    @Property("configuration.first_page")
    private int firstPage;

    @PropertyType(Types.INT)
    @Property("configuration.max_items_per_page")
    private int maxItemsPerPage;

    @PropertyType(Types.INT)
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
        if (instance == null) {
            instance = new Loader<Configuration>().loadProperties(Configuration.class);
        }

        return instance;
    }
}
