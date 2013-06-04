package com.formation.projet.business.dao.impl;

import com.formation.projet.core.exceptions.DaoException;
import com.formation.projet.business.beans.Computer;
import com.formation.projet.business.beans.PageState;
import com.formation.projet.business.dao.ComputerDao;
import com.formation.projet.core.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.formation.projet.business.dao.impl.ComputerQueryFactory.*;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 24/05/13
 * Time: 15:40
 */
public enum ComputerDaoImpl implements ComputerDao {
    instance;

    private final ConnectionFactory factory;

    private ComputerDaoImpl() {
        this.factory = ConnectionFactory.instance;
    }

    @Override
    public Computer find(long id) throws DaoException {
        Computer computer = null;

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            Connection connection = factory.getConnection();

            statement = makeFindStatement(connection, id);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                computer = mapComputer(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while calling find(int)", e);
        } finally {
            factory.silentClosing(statement, resultSet);
        }

        return computer;
    }

    @Override
    public List<Computer> findAll(PageState pageState) throws DaoException {
        List<Computer> computers = new ArrayList<Computer>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            Connection connection = factory.getConnection();

            statement = makeFindAllStatement(connection, pageState);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Computer computer = mapComputer(resultSet);

                computers.add(computer);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while calling findAll(PageState)", e);
        } finally {
            factory.silentClosing(statement, resultSet);
        }

        return computers;
    }

    @Override
    public Computer create(Computer computer) throws DaoException {
        PreparedStatement statement = null;

        try {
            Connection connection = factory.getConnection();

            statement = makeCreateStatement(connection, computer);
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                computer.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while calling create(Computer)", e);
        } finally {
            factory.silentClosing(statement);
        }

        return computer;
    }

    @Override
    public Computer update(Computer computer) throws DaoException {
        PreparedStatement statement = null;

        try {
            Connection connection = factory.getConnection();

            statement = makeUpdateStatement(connection, computer);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error while calling update(Computer)", e);
        } finally {
            factory.silentClosing(statement);
        }

        return find(computer.getId());
    }

    @Override
    public void delete(Computer computer) throws DaoException {
        PreparedStatement statement = null;

        try {
            Connection connection = factory.getConnection();

            statement = makeDeleteStatement(connection, computer);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error while calling delete(Computer)", e);
        } finally {
            factory.silentClosing(statement);
        }
    }

    @Override
    public int count(String filter) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        int count = -1;
        try {
            Connection connection = factory.getConnection();

            statement = makeCountStatement(connection, filter);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("value");
            }
        } catch (SQLException e) {
            throw new DaoException("Error while calling count(String)", e);
        } finally {
            factory.silentClosing(statement, resultSet);
        }

        return count;
    }
}
