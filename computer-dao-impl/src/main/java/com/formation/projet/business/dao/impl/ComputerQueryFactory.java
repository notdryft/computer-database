package com.formation.projet.business.dao.impl;

import com.formation.projet.business.beans.Company;
import com.formation.projet.business.beans.Computer;
import com.formation.projet.business.beans.PageState;
import com.formation.projet.core.helpers.DateHelper;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 03/06/13
 * Time: 14:42
 */
class ComputerQueryFactory {

    private static final String FIND_QUERY =
            "SELECT l.id, l.name, l.introduced, l.discontinued, r.id, r.name " +
                    "FROM computer l " +
                    "LEFT OUTER JOIN company r " +
                    "ON l.company_id = r.id";

    private static final String COUNT_QUERY =
            "SELECT COUNT(l.name) AS value FROM computer l";

    private static final String FILTER_ID_CLAUSE =
            " WHERE l.id = ?";

    private static final String FILTER_NAME_CLAUSE =
            " WHERE l.name LIKE ?";

    private static final String ORDER_BY_CLAUSE =
            " ORDER BY ";

    private static final String LIMIT_CLAUSE =
            " LIMIT ?, ?";

    private static final String INSERT_WITHOUT_COMPANY =
            "INSERT INTO computer (`name`, `introduced`, `discontinued`) VALUES (?, ?, ?)";

    private static final String INSERT_FULL =
            "INSERT INTO computer (`name`, `introduced`, `discontinued`, `company_id`) VALUES (?, ?, ?, ?)";

    private static final String UPDATE_QUERY =
            "UPDATE computer l SET l.name = ?, l.introduced = ?, l.discontinued = ?, company_id = ?";

    private static final String DELETE_QUERY =
            "DELETE FROM computer WHERE `id` = ?";

    private static final Map<Integer, String> COMPUTER_COLUMNS = new HashMap<Integer, String>();

    static {
        COMPUTER_COLUMNS.put(1, "l.id");
        COMPUTER_COLUMNS.put(2, "l.name");
        COMPUTER_COLUMNS.put(3, "l.introduced");
        COMPUTER_COLUMNS.put(4, "l.discontinued");
        COMPUTER_COLUMNS.put(5, "r.name");
    }

    public static Computer mapComputer(ResultSet resultSet) throws SQLException {
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

    public static PreparedStatement makeFindStatement(Connection connection, long id) throws SQLException {
        String query = FIND_QUERY + FILTER_ID_CLAUSE;
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);

        return statement;
    }

    public static PreparedStatement makeFindAllStatement(Connection connection, PageState pageState) throws SQLException {
        String column = COMPUTER_COLUMNS.get(Math.abs(pageState.getSortColumn()));

        String query = FIND_QUERY + FILTER_NAME_CLAUSE + ORDER_BY_CLAUSE + column + (pageState.getSortColumn() < 0 ? " DESC" : " ASC") + LIMIT_CLAUSE;
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, "%" + pageState.getFilter() + "%");
        statement.setInt(2, pageState.getOffset());
        statement.setInt(3, pageState.getMaxItemsPerPage());

        return statement;
    }

    public static PreparedStatement makeCreateStatement(Connection connection, Computer computer) throws SQLException {
        PreparedStatement statement;
        if (computer.getCompany() == null) {
            statement = connection.prepareStatement(INSERT_WITHOUT_COMPANY, Statement.RETURN_GENERATED_KEYS);
        } else {
            statement = connection.prepareStatement(INSERT_FULL, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(4, computer.getCompany().getId());
        }

        statement.setString(1, computer.getName());
        statement.setDate(2, DateHelper.toSQLDate(computer.getIntroduced()));
        statement.setDate(3, DateHelper.toSQLDate(computer.getDiscontinued()));

        return statement;
    }

    public static PreparedStatement makeUpdateStatement(Connection connection, Computer computer) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY + FILTER_ID_CLAUSE);
        statement.setString(1, computer.getName());
        statement.setDate(2, DateHelper.toSQLDate(computer.getIntroduced()));
        statement.setDate(3, DateHelper.toSQLDate(computer.getDiscontinued()));

        if (computer.getCompany() == null) {
            statement.setNull(4, Types.BIGINT);
        } else {
            statement.setLong(4, computer.getCompany().getId());
        }

        statement.setLong(5, computer.getId());

        return statement;
    }

    public static PreparedStatement makeDeleteStatement(Connection connection, Computer computer) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
        statement.setLong(1, computer.getId());

        return statement;
    }

    public static PreparedStatement makeCountStatement(Connection connection, String filter) throws SQLException {
        String query = COUNT_QUERY + FILTER_NAME_CLAUSE;

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, "%" + filter + "%");

        return statement;
    }
}
