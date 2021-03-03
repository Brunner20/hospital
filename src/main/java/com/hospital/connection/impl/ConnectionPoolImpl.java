package com.hospital.connection.impl;

import com.hospital.connection.ConnectionPool;
import com.hospital.connection.ConnectionPoolException;
import com.hospital.connection.data.DBParameter;
import com.hospital.connection.data.DBResourceManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPoolImpl implements ConnectionPool {

    private BlockingQueue<Connection>  connectionPool;
    private BlockingQueue<Connection> usedConnections;

    private String driver;
    private String url;
    private String user;
    private String password;
    private int size;

    public ConnectionPoolImpl() throws ConnectionPoolException {
        DBResourceManager resourceManager = DBResourceManager.getInstance();
        this.driver = resourceManager.getValue(DBParameter.DB_DRIVER);
        this.url = resourceManager.getValue(DBParameter.DB_URL);
        this.user = resourceManager.getValue(DBParameter.DB_USER);
        this.password = resourceManager.getValue(DBParameter.DB_PASSWORD);

        try {
            this.size = Integer.parseInt(resourceManager.getValue(DBParameter.DB_POOLSIZE));
        }catch (NumberFormatException e){
            this.size = 10;
        }
        init();
    }

    private void init()throws ConnectionPoolException{
        try {
            Class.forName(driver);
            connectionPool = new ArrayBlockingQueue<>(size);
            usedConnections = new ArrayBlockingQueue<>(size);

            for(int i =0;i<size;i++){
                Connection connection = DriverManager.getConnection(url,user,password);
                connectionPool.add(connection);
            }
        } catch (SQLException sqlE) {
            throw new ConnectionPoolException("can't connect to db,  check url,user,password",sqlE);
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("can't find database driver class",e);
        }
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = connectionPool.take();
            usedConnections.add(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }
}
