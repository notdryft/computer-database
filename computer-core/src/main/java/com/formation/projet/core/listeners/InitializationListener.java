package com.formation.projet.core.listeners;

import com.formation.projet.core.injectors.Injection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 31/05/13
 * Time: 13:39
 */
public class InitializationListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(InitializationListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("Computer-database core starting");

        Injection.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("Computer-database core shutdown");
    }
}
