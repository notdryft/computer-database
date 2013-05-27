package com.formation.projet.business.dao;

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
            resultSet = statement.executeQuery("SELECT * FROM computer");
            while (resultSet.next()) {
                Computer computer = new Computer();
                computer.setId(resultSet.getInt(1));
                computer.setName(resultSet.getString(2));
                computer.setIntroduced(resultSet.getDate(3));
                computer.setDiscontinued(resultSet.getDate(4));
                computer.setCompanyId(resultSet.getInt(5));

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
