package com.formation.projet.util;

import com.formation.projet.exceptions.PropertiesLoadingException;
import com.formation.projet.helpers.IntHelper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 30/05/13
 * Time: 17:43
 */
public class Properties {

    private static final String EMPTY_KEYWORD = "<empty>";

    private final String path;

    private final java.util.Properties properties;

    private Properties(String path) {
        this.path = path;
        this.properties = new java.util.Properties();
    }

    public int getInt(String property) {
        return IntHelper.parseProperty(properties, property);
    }

    public String getString(String property) {
        return properties.getProperty(property);
    }

    private void load(InputStream inputStream) throws IOException {
        properties.load(inputStream);

    }

    private void tryProperty(String property) {
        String value = properties.getProperty(property);
        if (value == null) {
            throw new PropertiesLoadingException("Property \"", property, "\" not found in file \"", path, "\"");
        } else if (value.isEmpty()) {
            throw new PropertiesLoadingException("Property \"", property, "\" empty in file \"", path, "\"");
        }
    }

    private void cleanTaggedProperties() {
        for (String property : properties.stringPropertyNames()) {
            String value = properties.getProperty(property);
            if (value.equals(EMPTY_KEYWORD)) {
                properties.remove(property);
                properties.put(property, "");
            }
        }
    }

    public static Properties loadProperties(String path, String... names) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        InputStream is = loader.getResourceAsStream(path);
        if (is == null) {
            throw new PropertiesLoadingException("File \"", path, "\" not found");
        }

        Properties properties = new Properties(path);
        try {
            properties.load(is);
        } catch (IOException e) {
            throw new PropertiesLoadingException(e, "Error while reading from \"", path, "\"");
        }

        for (String property : names) {
            properties.tryProperty(property);
        }

        properties.cleanTaggedProperties();

        return properties;
    }
}
