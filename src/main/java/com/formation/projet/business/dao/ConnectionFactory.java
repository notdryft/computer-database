package com.formation.projet.business.dao;

import com.formation.projet.application.properties.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 24/05/13
 * Time: 15:25
 */
public enum ConnectionFactory {
    instance;

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    private Database database;

    private ConnectionFactory() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        database = Database.getInstance();
    }

    public void openConnection() {
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
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return threadLocal.get();
    }

    public void closeConnection() {
        Connection connection = threadLocal.get();
        threadLocal.remove();

        DaoUtils.silentClosing(connection);
    }
}
