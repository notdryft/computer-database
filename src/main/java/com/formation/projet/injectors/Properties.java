package com.formation.projet.injectors;

import com.formation.projet.exceptions.ParseException;
import com.formation.projet.exceptions.PropertiesLoadingException;
import com.formation.projet.helpers.IntHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 31/05/13
 * Time: 12:12
 */
class Properties {

    private static final String EMPTY_KEYWORD = "<empty>";

    private String path;

    private final java.util.Properties properties;

    private Properties(String path) {
        this.path = path;
        this.properties = new java.util.Properties();
    }

    public int getInt(String property) throws ParseException {
        return IntHelper.parseProperty(properties, property);
    }

    public String getString(String property) {
        return properties.getProperty(property);
    }

    public List<String> getList(String property) {
        return Arrays.asList(properties.getProperty(property).split(","));
    }

    public void tryProperty(String property) throws PropertiesLoadingException {
        String value = properties.getProperty(property);
        if (value == null) {
            throw new PropertiesLoadingException(String.format("Property \"%s\" not found in file \"%s\"", property, path));
        } else if (value.isEmpty()) {
            throw new PropertiesLoadingException(String.format("Property \"%s\" empty in file \"%s\"", property, path));
        }
    }

    public void cleanTaggedProperty(String property) {
        String value = properties.getProperty(property);
        if (value.equals(EMPTY_KEYWORD)) {
            properties.remove(property);
            properties.put(property, "");
        }
    }

    private void load(InputStream inputStream) throws IOException {
        properties.load(inputStream);

    }

    public static Properties loadProperties(String path) throws PropertiesLoadingException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        InputStream is = classLoader.getResourceAsStream(path);
        if (is == null) {
            throw new PropertiesLoadingException(String.format("File \"%s\" not found", path));
        }

        Properties p = new Properties(path);
        try {
            p.load(is);
        } catch (IOException e) {
            throw new PropertiesLoadingException(String.format("Error while reading from \"%s\"", path), e);
        }

        return p;
    }
}
