package com.hospital.dao.connection;

import com.hospital.dao.connection.impl.ConnectionPoolImpl;

/**
 * The class that serves as a provider for the pool of connections
 */
public final class PoolProvider {

    private PoolProvider() {}

    /**
     * Instance of {@link PoolProvider}
     */
    private static ConnectionPool  connectionPool = new ConnectionPoolImpl();

    public static ConnectionPool getConnectionPool() { return connectionPool;}
}
