package com.formation.projet.business.dao;

import com.formation.projet.business.exceptions.PropertiesLoadingException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

        Properties p = new Properties();
        try {
            p.load(is);
        } catch (IOException e) {
            throw new PropertiesLoadingException("File \"" + propertiesPath + "\" not found", e);
        }

        DatabaseProperties properties = new DatabaseProperties();
        properties.setUrl(p.getProperty("db.url"));
        properties.setSchema(p.getProperty("db.schema"));
        properties.setUser(p.getProperty("db.user"));
        properties.setPassword(p.getProperty("db.password"));

        // TODO what if property is not resolved ?

        return properties;
    }

    public static void silentClosing(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void silentClosing(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void silentClosing(Statement statement, ResultSet resultSet) {
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
