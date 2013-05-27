package com.formation.projet.business.dao;

import com.formation.projet.business.beans.Company;
import com.formation.projet.business.beans.Computer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
                    "FROM computer l LEFT OUTER JOIN company r ON l.company_id = r.id";

    private ConnectionFactory factory;

    private ComputerDaoImpl() {
        this.factory = ConnectionFactory.instance;
    }

    @Override
    public Computer find(int id) {
        return null;
    }

    @Override
    public List<Computer> findAll() {
        Connection connection = factory.getConnection();

        List<Computer> computers = new ArrayList<Computer>();

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_QUERY);
            while (resultSet.next()) {
                Computer computer = new Computer();
                computer.setId(resultSet.getInt("l.id"));
                computer.setName(resultSet.getString("l.name"));
                computer.setIntroduced(resultSet.getDate("l.introduced"));
                computer.setDiscontinued(resultSet.getDate("l.discontinued"));

                if (resultSet.getObject("r.id") == null) {
                    computer.setCompany(null);
                } else {
                    Company company = new Company();
                    company.setId(resultSet.getInt("r.id"));
                    company.setName(resultSet.getString("r.name"));

                    computer.setCompany(company);
                }

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
}
