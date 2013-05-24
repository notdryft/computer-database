package com.formation.projet.business.dao;

import com.formation.projet.business.exceptions.ConnectionFactoryCreationException;
import com.formation.projet.business.exceptions.PropertiesLoadingException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 24/05/13
 * Time: 15:25
 */
public class ConnectionFactory {

    private static ConnectionFactory instance;

    private DatabaseProperties properties;

    private ConnectionFactory() {
        // Do nothing.
    }

    public static ConnectionFactory getInstance() {
        if (instance == null) {
            instance = new ConnectionFactory();

            try {
                instance.init();
            } catch (ConnectionFactoryCreationException e) {
                e.printStackTrace();

                instance = null;
            }
        }

        return instance;
    }

    private void init() throws ConnectionFactoryCreationException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ConnectionFactoryCreationException(e);
        }

        try {
            properties = DaoUtils.getProperties();
        } catch (PropertiesLoadingException e) {
            throw new ConnectionFactoryCreationException(e);
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    properties.getUrl() + properties.getSchema(),
                    properties.getUser(),
                    properties.getSchema()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
