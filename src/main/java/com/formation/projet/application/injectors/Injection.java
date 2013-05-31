package com.formation.projet.application.injectors;

import com.formation.projet.application.annotations.Property;
import com.formation.projet.application.annotations.PropertyClass;
import com.formation.projet.application.exceptions.InjectionException;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 31/05/13
 * Time: 13:44
 */
@PropertyClass("application.properties")
public class Injection {

    private static Injection instance = null;

    @Property("application.packagesToScan")
    private List<String> packagesToScan;

    @Property("application.beans")
    private List<String> beans;

    private Injection() {
        // Do nothing.
    }

    private List<String> getPackagesToScan() {
        return packagesToScan;
    }

    private List<String> getBeans() {
        return beans;
    }

    private static Injection getInstance() {
        if (instance == null) {
            instance = Injector.loadProperties(Injection.class);
        }

        return instance;
    }

    private static void start0() throws Exception {
        Injection application = getInstance();
        for (String pkg : application.getPackagesToScan()) {
            for (String bean : application.getBeans()) {
                String beanName = pkg + "." + bean;

                Class<?> clazz = Class.forName(beanName);

                Field field = clazz.getDeclaredField("instance");
                field.setAccessible(true);
                field.set(null, Injector.loadProperties(clazz));
            }
        }
    }

    public static void start() {
        try {
            start0();
        } catch (Exception e) {
            throw new InjectionException("Failed to inject properties beans", e);
        }
    }
}
