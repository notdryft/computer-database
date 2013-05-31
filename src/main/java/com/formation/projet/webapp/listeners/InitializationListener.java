package com.formation.projet.webapp.listeners;

import com.formation.projet.application.Application;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 31/05/13
 * Time: 13:39
 */
public class InitializationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Application.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // Do nothing.
    }
}
