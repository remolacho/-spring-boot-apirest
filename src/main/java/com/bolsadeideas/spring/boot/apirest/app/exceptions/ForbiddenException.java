package com.bolsadeideas.spring.boot.apirest.app.exceptions;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}
