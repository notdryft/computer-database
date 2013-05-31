package com.formation.projet;

import com.formation.projet.annotations.Property;
import com.formation.projet.annotations.PropertyClass;
import com.formation.projet.injectors.Injector;

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
            instance = new Injector<Application>().loadProperties(Application.class);
        }

        return instance;
    }
}
