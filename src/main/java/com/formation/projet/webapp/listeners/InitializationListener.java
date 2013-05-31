package com.formation.projet.webapp.listeners;

import com.formation.projet.application.Application;
import com.formation.projet.application.exceptions.InjectionException;
import com.formation.projet.application.injectors.Injector;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 31/05/13
 * Time: 13:39
 */
public class InitializationListener implements ServletContextListener {

    private void contextInitialized0(ServletContextEvent servletContextEvent) throws Exception {
        Application application = Application.getInstance();

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

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            contextInitialized0(servletContextEvent);
        } catch (Exception e) {
            throw new InjectionException("Failed to inject properties beans", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // Do nothing.
    }
}
