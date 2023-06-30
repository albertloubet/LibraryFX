package com.github.albertloubet.libraryfx.exception;

public class ServiceException extends RuntimeException {
    public ServiceException(Exception e) {
        super(e);
    }
}
