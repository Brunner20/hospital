package com.hospital.service.exception;

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
