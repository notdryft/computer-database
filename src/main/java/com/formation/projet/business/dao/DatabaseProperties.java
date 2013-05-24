package com.formation.projet.business.dao;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 24/05/13
 * Time: 16:38
 */
public class DatabaseProperties {

    private String url;

    private String schema;

    private String user;

    private String password;

    public DatabaseProperties() {
        // Do nothing
    }

    public DatabaseProperties(String url, String schema, String user, String password) {
        this.url = url;
        this.schema = schema;
        this.user = user;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
