package com.formation.projet.business.beans;

import com.formation.projet.core.properties.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 30/05/13
 * Time: 16:44
 */
public class PageState {

    private final Configuration configuration;

    private int page;

    private int sortColumn;

    private String filter;

    private int total;

    public PageState() {
        configuration = Configuration.getInstance();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(int sortColumn) {
        this.sortColumn = sortColumn;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOffset() {
        return page * configuration.getMaxItemsPerPage();
    }

    public int getMaxPages() {
        return total / configuration.getMaxItemsPerPage();
    }

    public int getMaxItemsPerPage() {
        return configuration.getMaxItemsPerPage();
    }
}
