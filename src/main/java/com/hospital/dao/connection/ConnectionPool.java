package com.hospital.dao.connection;

import java.sql.Connection;

public interface ConnectionPool {

    Connection getConnection() throws ConnectionPoolException;
    boolean releaseConnection(Connection connection);

}
