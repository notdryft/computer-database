package com.formation.projet.util;

import com.formation.projet.exceptions.PropertiesLoadingException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

    private static final String EMPTY_KEYWORD = "<empty>";

    public static Properties loadProperties(String path, String... names) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        InputStream is = loader.getResourceAsStream(path);
        if (is == null) {
            throw new PropertiesLoadingException("File \"", path, "\" not found");
        }

        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            throw new PropertiesLoadingException(e, "Error while reading from \"", path, "\"");
        }

        for (String name : names) {
            tryProperty(properties, path, name);
        }

        return properties;
    }

    private static void tryProperty(Properties properties, String path, String name) {
        String value = properties.getProperty(name);
        if (value == null) {
            throw new PropertiesLoadingException("Property \"", name, "\" not found in file \"", path, "\"");
        } else if (value.isEmpty()) {
            throw new PropertiesLoadingException("Property \"", name, "\" empty in file \"", path, "\"");
        }

        if (value.equals(EMPTY_KEYWORD)) {
            properties.remove(name);
            properties.put(name, "");
        }
    }
}
