package com.hospital.dao.connection;

import com.hospital.dao.connection.impl.ConnectionPoolImpl;


public final class PoolProvider {

    private PoolProvider() {}

    private static ConnectionPool  connectionPool = null;

    static {
        try {
            connectionPool = new ConnectionPoolImpl();
        } catch (ConnectionPoolException e) {
             throw new PoolProviderException(e);
        }
    }

    public static ConnectionPool getConnectionPool() {return connectionPool;}
}
