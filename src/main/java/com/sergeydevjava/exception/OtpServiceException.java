package com.sergeydevjava.exception;

public class OtpServiceException extends RuntimeException {
    public OtpServiceException(String errorMessage) {
        super(errorMessage);
    }

    public OtpServiceException(String errorMessage, Exception exception) {
        super(errorMessage, exception);
    }
}
