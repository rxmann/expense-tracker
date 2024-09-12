package com.codex.exceptions;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException() {
        super("Invalid Request!");
    }

    public InvalidRequestException(String message) {
        super(message);
    }
}
