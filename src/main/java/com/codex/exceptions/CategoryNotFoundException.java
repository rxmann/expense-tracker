package com.codex.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException (String message) {
        super(message);
    }
}
