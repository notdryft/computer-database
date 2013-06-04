package com.formation.projet.core.injectors;

import com.formation.projet.core.annotations.Property;
import com.formation.projet.core.annotations.PropertyClass;
import com.formation.projet.core.exceptions.InjectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger logger = LoggerFactory.getLogger(Injection.class);

    private static Injection instance = null;

    @Property("application.packagesToScan")
    private List<String> packagesToScan;

    @Property("application.beans")
    private List<String> beans;

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
        logger.debug("Starting core properties injection");

        try {
            start0();
        } catch (Exception e) {
            logger.error("Failed to inject properties beans", e);

            throw new InjectionException("Failed to inject properties beans", e);
        }
    }
}
