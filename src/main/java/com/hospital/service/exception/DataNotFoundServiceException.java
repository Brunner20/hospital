package com.hospital.service.exception;

/**
 * The type service exception when data not found.
 */
public class DataNotFoundServiceException extends ServiceException{
    public DataNotFoundServiceException() {
        super();
    }

    public DataNotFoundServiceException(String message) {
        super(message);
    }

    public DataNotFoundServiceException(Exception e) {
        super(e);
    }

    public DataNotFoundServiceException(String message, Exception e) {
        super(message, e);
    }
}
