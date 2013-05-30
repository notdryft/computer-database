package com.formation.projet.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 30/05/13
 * Time: 17:43
 */
public class Properties {

    private final String path;

    private final java.util.Properties properties;

    public Properties(String path) {
        this.path = path;
        this.properties = new java.util.Properties();
    }

    public String getPath() {
        return path;
    }

    public void load(InputStream inputStream) throws IOException {
        properties.load(inputStream);
    }

    public int getInt(String property) {
        return 0;
    }

    public String getString(String property) {
        return null;
    }
}
