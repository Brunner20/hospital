package com.hospital.connection;

import com.hospital.connection.impl.ConnectionPoolImpl;


public class PoolProvider {

    private PoolProvider() {}

    private static ConnectionPool instance;

    static {
        try {
            instance = new ConnectionPoolImpl();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getConnectionPool() {return instance;}
}
