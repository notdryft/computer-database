package com.formation.projet.business.dao;

import com.formation.projet.business.exceptions.PropertiesLoadingException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 24/05/13
 * Time: 16:29
 */
public class DaoUtils {

    private static String propertiesPath = "database.properties";

    public static DatabaseProperties getProperties() throws PropertiesLoadingException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream is = loader.getResourceAsStream(propertiesPath);

        if (is == null) {
            throw new PropertiesLoadingException("File \"" + propertiesPath + "\" not found");
        }

        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            throw new PropertiesLoadingException("File \"" + propertiesPath + "\" not found", e);
        }

        DatabaseProperties databaseProperties = new DatabaseProperties();
        databaseProperties.setUrl(properties.getProperty("db.url"));
        databaseProperties.setSchema(properties.getProperty("db.schema"));
        databaseProperties.setUser(properties.getProperty("db.user"));
        databaseProperties.setPassword(properties.getProperty("db.password"));

        return databaseProperties;
    }

    public static void silentClosing(Connection connection, PreparedStatement preparedStatement) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void silentClosing(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        silentClosing(connection, preparedStatement);
    }
}
