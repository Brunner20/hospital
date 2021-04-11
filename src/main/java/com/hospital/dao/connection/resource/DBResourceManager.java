package com.hospital.dao.connection.resource;

import java.util.ResourceBundle;

/**
 * The class resource manager for database
 */
public final class DBResourceManager {

    private final static DBResourceManager instance = new DBResourceManager();

    private ResourceBundle bundle = ResourceBundle.getBundle("db");

    public static DBResourceManager getInstance(){
        return instance;
    }

    public String getValue(String key){
        return bundle.getString(key);
    }
}
