package com.formation.projet.connection;

import com.formation.projet.core.exceptions.ConnectionException;
import com.formation.projet.core.properties.Database;
import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 24/05/13
 * Time: 15:25
 */
@Component
public class ConnectionFactory {

    private final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    private final Database database;

    public ConnectionFactory() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ConnectionException("Driver not found", e);
        }

        database = Database.getInstance();
    }

    public Connection openConnection() {
        Connection connection = threadLocal.get();
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        database.getUrl() + database.getSchema(),
                        database.getUser(),
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
