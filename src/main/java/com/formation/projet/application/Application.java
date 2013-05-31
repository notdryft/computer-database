package com.formation.projet.application;

import com.formation.projet.application.annotations.Property;
import com.formation.projet.application.annotations.PropertyClass;
import com.formation.projet.application.injectors.Injector;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 31/05/13
 * Time: 13:44
 */
@PropertyClass("application.properties")
public class Application {

    private static Application instance = null;

    @Property("application.packagesToScan")
    private List<String> packagesToScan;

    @Property("application.beans")
    private List<String> beans;

    private Application() {
        // Do nothing.
    }

    public List<String> getPackagesToScan() {
        return packagesToScan;
    }

    public List<String> getBeans() {
        return beans;
    }

    public static Application getInstance() {
        if (instance == null) {
            instance = Injector.loadProperties(Application.class);
        }

        return instance;
    }
}
