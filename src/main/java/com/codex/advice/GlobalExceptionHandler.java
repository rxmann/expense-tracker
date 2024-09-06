package com.codex.advice;


import com.codex.exceptions.CategoryNotFoundException;
import com.codex.exceptions.CustomErrorResponse;
import com.codex.exceptions.ExpenseNotFoundException;
import com.codex.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleUserNotFoundException(UserNotFoundException exp) {
        return new ResponseEntity<>(new CustomErrorResponse(exp.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleCategoryNotFoundException(CategoryNotFoundException exp) {
        return new ResponseEntity<>(new CustomErrorResponse(exp.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleExpenseNotFoundException(CategoryNotFoundException exp) {
        return new ResponseEntity<>(new CustomErrorResponse(exp.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }


}
