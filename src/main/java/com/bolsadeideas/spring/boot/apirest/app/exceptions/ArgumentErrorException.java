package com.bolsadeideas.spring.boot.apirest.app.exceptions;

public class ArgumentErrorException extends RuntimeException {
    public ArgumentErrorException(String message) {
        super(message);
    }
}
