package com.formation.projet.properties;


import com.formation.projet.annotations.Property;
import com.formation.projet.annotations.PropertyClass;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 24/05/13
 * Time: 16:38
 */
@PropertyClass("database.properties")
public class Database {

    private static Database instance;

    @Property("db.url")
    private String url;

    @Property("db.schema")
    private String schema;

    @Property("db.user")
    private String user;

    @Property("db.password")
    private String password;

    public Database() {
        // Do nothing
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

    public static Database getInstance() {
        return instance;
    }
}
