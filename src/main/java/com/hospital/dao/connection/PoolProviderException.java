package com.hospital.dao.connection;

public class PoolProviderException extends RuntimeException{

    public PoolProviderException() {
        super();
    }

    public PoolProviderException(String message) {
        super(message);
    }

    public PoolProviderException(String message, Throwable cause) {
        super(message, cause);
    }

    public PoolProviderException(Throwable cause) {
        super(cause);
    }
}
