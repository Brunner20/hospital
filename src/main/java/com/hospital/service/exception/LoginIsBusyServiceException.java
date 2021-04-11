package com.hospital.service.exception;

/**
 * The type service exception when login is busy.
 */
public class LoginIsBusyServiceException extends ServiceException {
    public LoginIsBusyServiceException() {
        super();
    }

    public LoginIsBusyServiceException(String message) {
        super(message);
    }

    public LoginIsBusyServiceException(Exception e) {
        super(e);
    }

    public LoginIsBusyServiceException(String message, Exception e) {
        super(message, e);
    }
}
