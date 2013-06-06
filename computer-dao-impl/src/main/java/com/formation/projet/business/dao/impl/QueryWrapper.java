package com.formation.projet.business.dao.impl;

import org.springframework.jdbc.core.RowMapper;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 05/06/13
 * Time: 16:57
 */
class QueryWrapper<T> {

    private String query;

    private Object[] values;

    private RowMapper<T> rowMapper;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }

    public RowMapper<T> getRowMapper() {
        return rowMapper;
    }

    public void setRowMapper(RowMapper<T> rowMapper) {
        this.rowMapper = rowMapper;
    }
}
