package com.hospital.dao.connection.impl;

import com.hospital.dao.DAOException;
import com.hospital.dao.connection.ConnectionPool;
import com.hospital.dao.connection.ConnectionPoolException;
import com.hospital.dao.connection.resource.DBParameter;
import com.hospital.dao.connection.resource.DBResourceManager;

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
    public Connection getConnection() throws ConnectionPoolException {
        Connection connection = null;
        try {
            connection = connectionPool.take();
            usedConnections.add(connection);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("can't take connection",e);
        }
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        if(connection!=null)
        {
            usedConnections.remove(connection);
            return connectionPool.add(connection);
        }
        return false;
    }

    public void dispose()throws DAOException{
        try {
            clearConnectionQueue();
        } catch (SQLException throwables) {
            throw new DAOException(throwables);
        }
    }

    private void clearConnectionQueue() throws SQLException {
        closeConnectionQueue(connectionPool);
        closeConnectionQueue(usedConnections);
    }

    private void closeConnectionQueue(BlockingQueue<Connection> connectionPool) throws SQLException {

        Connection connection;
        while ((connection= connectionPool.poll())!=null){
            if(!connection.getAutoCommit()) {
               connection.commit();
            }
           connection.close();
        }

    }
}
