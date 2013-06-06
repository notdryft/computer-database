package com.formation.projet.business.dao.impl;

import com.formation.projet.business.beans.Company;
import com.formation.projet.business.beans.Computer;
import com.formation.projet.core.helpers.DateHelper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 05/06/13
 * Time: 16:09
 */
public class ComputerMapper implements RowMapper<Computer> {

    @Override
    public Computer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Computer computer = new Computer();
        computer.setId(resultSet.getLong("l.id"));
        computer.setName(resultSet.getString("l.name"));
        computer.setIntroduced(DateHelper.toDate(resultSet.getDate("l.introduced")));
        computer.setDiscontinued(DateHelper.toDate(resultSet.getDate("l.discontinued")));

        Object companyId = resultSet.getObject("r.id");
        if (companyId == null) {
            computer.setCompany(null);
        } else {
            Company company = new Company();
            company.setId((Long) companyId);
            company.setName(resultSet.getString("r.name"));

            computer.setCompany(company);
        }

        return computer;
    }
}
