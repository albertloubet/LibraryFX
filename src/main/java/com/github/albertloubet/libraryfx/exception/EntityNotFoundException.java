package com.github.albertloubet.libraryfx.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id) {
        super(String.format("Entity not found for value %s", id));
    }
}
