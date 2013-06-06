package com.formation.projet.business.dao.impl;

import com.formation.projet.business.beans.Computer;
import com.formation.projet.business.beans.PageState;
import com.formation.projet.core.helpers.DateHelper;
import org.springframework.jdbc.core.PreparedStatementCreator;

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

    public static String makeFindQuery() {

        return FIND_QUERY + FILTER_ID_CLAUSE;
    }

    public static PreparedStatementCreator makeFindAllStatement(final PageState pageState) {
        String column = COMPUTER_COLUMNS.get(Math.abs(pageState.getSortColumn()));

        StringBuilder sb = new StringBuilder();
        sb.append(FIND_QUERY);
        sb.append(FILTER_NAME_CLAUSE);
        sb.append(ORDER_BY_CLAUSE);
        sb.append(column);
        sb.append(pageState.getSortColumn() < 0 ? " DESC" : " ASC");
        sb.append(LIMIT_CLAUSE);

        final String query = sb.toString();

        return new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement(query);

                statement.setString(1, "%" + pageState.getFilter() + "%");
                statement.setInt(2, pageState.getOffset());
                statement.setInt(3, pageState.getMaxItemsPerPage());

                return statement;
            }
        };
    }

    public static PreparedStatementCreator makeCreateStatement(final Computer computer) {

        return new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
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
        };
    }

    public static PreparedStatementCreator makeUpdateStatement(final Computer computer) {

        return new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
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
        };
    }

    public static PreparedStatementCreator makeDeleteStatement(final Computer computer) {

        return new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
                statement.setLong(1, computer.getId());

                return statement;
            }
        };
    }

    public static String makeCountQuery() {

        return COUNT_QUERY + FILTER_NAME_CLAUSE;
    }
}
