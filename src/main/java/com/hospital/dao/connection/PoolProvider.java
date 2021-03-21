package com.hospital.dao.connection;

import com.hospital.dao.connection.impl.ConnectionPoolImpl;


public final class PoolProvider {

    private PoolProvider() {}

    private static ConnectionPool  connectionPool = new ConnectionPoolImpl();


    public static ConnectionPool getConnectionPool() {
        try {
            connectionPool.init();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        return connectionPool;}
}
