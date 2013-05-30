package com.formation.projet.properties;


import com.formation.projet.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 24/05/13
 * Time: 16:38
 */
public enum Database {
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

    private Database() {
        Properties properties =
                Properties.loadProperties(
                        PATH,
                        URL, SCHEMA, USER, PASSWORD);

        this.url = properties.getString(URL);
        this.schema = properties.getString(SCHEMA);
        this.user = properties.getString(USER);
        this.password = properties.getString(PASSWORD);
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
