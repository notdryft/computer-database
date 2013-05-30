package com.formation.projet.configuration;

import com.formation.projet.util.PropertiesUtils;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 24/05/13
 * Time: 16:38
 */
public enum DatabaseProperties {
    instance;

    private static final String PATH = "database.properties";

    private static final String URL = "db.url";

    private static final String SCHEMA = "db.schema";

    private static final String USER = "db.user";

    private static final String PASSWORD = "db.password";

    private String url;

    private String schema;

    private String user;

    private String password;

    private DatabaseProperties() {
        Properties properties =
                PropertiesUtils.loadProperties(PATH, URL, SCHEMA, USER, PASSWORD);

        this.url = properties.getProperty(URL);
        this.schema = properties.getProperty(SCHEMA);
        this.user = properties.getProperty(USER);
        this.password = properties.getProperty(PASSWORD);
    }

    public String getUrl() {
        return url;
    }

    public String getSchema() {
        return schema;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
