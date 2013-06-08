package com.formation.projet.core.connection;

import com.formation.projet.core.exceptions.ConnectionException;
import com.formation.projet.core.properties.Database;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 24/05/13
 * Time: 15:25
 */
public enum ConnectionFactory {
    instance;

    private final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    private final Database database;

    private ConnectionFactory() {
        database = Database.getInstance();

        try {
            Class.forName(database.getDriverClassName());
        } catch (ClassNotFoundException e) {
            throw new ConnectionException("Driver not found", e);
        }
    }

    public Connection openConnection() {
        Connection connection = threadLocal.get();
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        database.getUrl(),
                        database.getUsername(),
                        database.getPassword()
                );

                threadLocal.set(connection);
            } catch (SQLException e) {
                throw new ConnectionException("Could not connect to the database", e);
            }
        }

        return connection;
    }

    public Connection getConnection() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection == null) {
            throw new SQLException("Connection not initialized, try openConnection() first");
        }

        return connection;
    }

    public void closeConnection() {
        Connection connection = threadLocal.get();
        threadLocal.remove();

        silentClosing(connection);
    }

    private void silentClosing(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void silentClosing(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void silentClosing(Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        silentClosing(statement);
    }
}
