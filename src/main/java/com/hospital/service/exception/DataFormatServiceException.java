package com.hospital.service.exception;


/**
 * The type service exception for invalid data.
 */
public class DataFormatServiceException extends ServiceException{
    private static final long serialVersionUID = 1L;

    public DataFormatServiceException() {
        super();
    }

    public DataFormatServiceException(String message) {
        super(message);
    }

    public DataFormatServiceException(Exception e) {
        super(e);
    }

    public DataFormatServiceException(String message, Exception e) {
        super(message, e);
    }
}
