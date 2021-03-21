package com.hospital.connection;

import com.hospital.connection.impl.ConnectionPoolImpl;
import com.hospital.controller.command.impl.GoToMainPage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PoolProvider {

    public static final Logger logger = LogManager.getLogger(PoolProvider.class);


    private PoolProvider() {}

    private static ConnectionPool instance;

    static {
        try {
            instance = new ConnectionPoolImpl();
        } catch (ConnectionPoolException e) {
            logger.log(Level.ERROR,"error in connect to database"+e.getMessage());
            e.printStackTrace();
        }
    }

    public static ConnectionPool getConnectionPool() {return instance;}
}
