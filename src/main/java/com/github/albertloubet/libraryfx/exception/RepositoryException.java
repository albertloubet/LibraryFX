package com.github.albertloubet.libraryfx.exception;

public class RepositoryException extends RuntimeException {
    public RepositoryException(Exception e) {
        super(e);
    }
}