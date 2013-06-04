package com.formation.projet.business.dao.impl;

import com.formation.projet.core.exceptions.DaoException;
import com.formation.projet.business.beans.Company;
import com.formation.projet.business.dao.CompanyDao;
import com.formation.projet.core.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 28/05/13
 * Time: 12:21
 */
public enum CompanyDaoImpl implements CompanyDao {
    instance;

    private static final String FIND_ALL_QUERY =
            "SELECT l.id, l.name " +
                    "FROM company l " +
                    "ORDER BY l.name ASC";

    private final ConnectionFactory factory;

    private CompanyDaoImpl() {
        factory = ConnectionFactory.instance;
    }

    private Company mapCompany(ResultSet resultSet) throws SQLException {
        Company company = new Company();
        company.setId(resultSet.getLong("l.id"));
        company.setName(resultSet.getString("l.name"));

        return company;
    }

    @Override
    public List<Company> findAll() throws DaoException {
        List<Company> companies = new ArrayList<Company>();

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Connection connection = factory.getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery(FIND_ALL_QUERY);
            while (resultSet.next()) {
                Company company = mapCompany(resultSet);

                companies.add(company);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while calling findAll()", e);
        } finally {
            factory.silentClosing(statement, resultSet);
        }

        return companies;
    }
}
