package com.formation.projet.core.injectors;

import com.formation.projet.core.exceptions.ParseException;
import com.formation.projet.core.exceptions.PropertiesLoadingException;
import com.formation.projet.webapp.helpers.IntHelper;
import com.formation.projet.webapp.helpers.StringHelper;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
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

    private final String path;

    private final java.util.Properties properties;

    private Properties(String path) {
        this.path = path;
        this.properties = new java.util.Properties();
    }

    public Method getMethod(Class<?> fieldClass) throws NoSuchMethodException {
        String methodName = "get" + StringHelper.capitalize(fieldClass.getSimpleName());

        return getClass().getMethod(methodName, String.class);
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
