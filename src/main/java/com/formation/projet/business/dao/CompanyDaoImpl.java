package com.formation.projet.business.dao;

import com.formation.projet.business.beans.Company;

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

    private static String FIND_ALL_QUERY =
            "SELECT l.id, l.name " +
                    "FROM company l " +
                    "ORDER BY l.name ASC";

    private ConnectionFactory factory;

    private CompanyDaoImpl() {
        this.factory = ConnectionFactory.instance;
    }

    private Company mapCompany(ResultSet resultSet) throws SQLException {
        Company company = new Company();
        company.setId(resultSet.getLong("l.id"));
        company.setName(resultSet.getString("l.name"));

        return company;
    }

    @Override
    public List<Company> findAll() {
        Connection connection = factory.getConnection();

        List<Company> companies = new ArrayList<Company>();

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();

            resultSet = statement.executeQuery(FIND_ALL_QUERY);
            while (resultSet.next()) {
                Company company = mapCompany(resultSet);

                companies.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DaoUtils.silentClosing(connection, statement, resultSet);
        }

        return companies;
    }
}
