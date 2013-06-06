package com.formation.projet.business.dao.impl;

import com.formation.projet.business.beans.Company;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 05/06/13
 * Time: 16:01
 */
public class CompanyMapper implements RowMapper<Company> {

    @Override
    public Company mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Company company = new Company();
        company.setId(resultSet.getLong("l.id"));
        company.setName(resultSet.getString("l.name"));

        return company;
    }
}
