package com.formation.projet.business.dao;

import com.formation.projet.business.beans.Company;
import com.formation.projet.business.beans.Computer;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 24/05/13
 * Time: 15:40
 */
public enum ComputerDaoImpl implements ComputerDao {
    instance;

    private static String FIND_ALL_QUERY =
            "SELECT l.id, l.name, l.introduced, l.discontinued, r.id, r.name " +
                    "FROM computer l " +
                    "LEFT OUTER JOIN company r " +
                    "ON l.company_id = r.id";

    private static String ORDER_BY_CLAUSE = " ORDER BY ";

    private static Map<Integer, String> COMPUTER_COLUMNS = new HashMap<Integer, String>();

    static {
        COMPUTER_COLUMNS.put(1, "l.id");
        COMPUTER_COLUMNS.put(2, "l.name");
        COMPUTER_COLUMNS.put(3, "l.introduced");
        COMPUTER_COLUMNS.put(4, "l.discontinued");
        COMPUTER_COLUMNS.put(5, "r.name");
    }

    private static String LIMIT_CLAUSE = " LIMIT ?, ?";

    private static String COUNT_QUERY =
            "SELECT COUNT(*) AS value FROM computer";

    private ConnectionFactory factory;

    private ComputerDaoImpl() {
        this.factory = ConnectionFactory.instance;
    }

    private Computer mapComputerWithCompany(ResultSet resultSet) throws SQLException {
        Computer computer = new Computer();
        computer.setId(resultSet.getInt("l.id"));
        computer.setName(resultSet.getString("l.name"));
        computer.setIntroduced(resultSet.getDate("l.introduced"));
        computer.setDiscontinued(resultSet.getDate("l.discontinued"));

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

    private PreparedStatement makeFindAllStatement(Connection connection, int sortColumn, int offset, int limit) throws SQLException {
        String column = COMPUTER_COLUMNS.get(Math.abs(sortColumn));

        String query = FIND_ALL_QUERY + ORDER_BY_CLAUSE + column + (sortColumn < 0 ? " DESC" : " ASC") + LIMIT_CLAUSE;
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, offset);
        statement.setInt(2, limit);

        return statement;
    }

    @Override
    public Computer find(int id) {
        return null;
    }

    @Override
    public List<Computer> findAll(int offset, int limit, int sortColumn) {
        Connection connection = factory.getConnection();

        List<Computer> computers = new ArrayList<Computer>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = makeFindAllStatement(connection, sortColumn, offset, limit);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Computer computer = mapComputerWithCompany(resultSet);

                computers.add(computer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DaoUtils.silentClosing(connection, statement, resultSet);
        }

        return computers;
    }

    @Override
    public Computer findByName(String name) {
        return null;
    }

    @Override
    public List<Computer> searchByName(String name) {
        return null;
    }

    @Override
    public Computer create(Computer computer) {
        return null;
    }

    @Override
    public Computer update(Computer computer) {
        return null;
    }

    @Override
    public void delete(Computer computer) {

    }

    @Override
    public int count() {
        Connection connection = factory.getConnection();

        Statement statement = null;
        ResultSet resultSet = null;

        int count = -1;
        try {
            statement = connection.createStatement();

            resultSet = statement.executeQuery(COUNT_QUERY);
            if (resultSet.next()) {
                count = resultSet.getInt("value");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DaoUtils.silentClosing(connection, statement, resultSet);
        }

        return count;
    }
}
