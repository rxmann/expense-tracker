package com.codex.exceptions;

public class ExpenseNotFoundException extends RuntimeException {
    public ExpenseNotFoundException (String message) {
        super(message);
    }
}
