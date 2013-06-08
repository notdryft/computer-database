package com.formation.projet.core.properties;


import com.formation.projet.core.annotations.Property;
import com.formation.projet.core.annotations.PropertyClass;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 24/05/13
 * Time: 16:38
 */
@PropertyClass("database.properties")
public class Database {

    private static Database instance;

    @Property("database.driverClassName")
    private String driverClassName;

    @Property("database.url")
    private String url;

    @Property("database.username")
    private String username;

    @Property("database.password")
    private String password;

    public Database() {
        // Do nothing
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static Database getInstance() {
        return instance;
    }
}
