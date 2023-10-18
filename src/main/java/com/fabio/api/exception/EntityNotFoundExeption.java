package com.fabio.api.exception;

public class EntityNotFoundExeption extends RuntimeException {
    public EntityNotFoundExeption(String message) {
        super(message);
    }
}
