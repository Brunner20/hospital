package com.hospital.controller.listener;

import com.hospital.dao.connection.ConnectionPoolException;
import com.hospital.dao.connection.PoolProvider;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class AppContextListener implements ServletContextListener {

    private static final String GO_TO_ERROR ="/WEB-INF/jsp/error.jsp";
    private static final String UNABLE_TO_INIT_POOL="Error while initializing pool data";
    private static final String ERROR_ON_CLOSING_POOL="Error while closing connection pool";
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            PoolProvider.getConnectionPool().init();
        } catch (ConnectionPoolException e){
            servletContextEvent.getServletContext().getRequestDispatcher(GO_TO_ERROR);

        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            PoolProvider.getConnectionPool().dispose();
        } catch (ConnectionPoolException e){

        }
    }
}
