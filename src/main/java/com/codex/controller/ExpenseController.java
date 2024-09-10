package com.codex.controller;


import com.codex.DTO.ExpenseDTO;
import com.codex.exceptions.ExpenseNotFoundException;
import com.codex.exceptions.InvalidRequestException;
import com.codex.model.Expense;
import com.codex.model.User;
import com.codex.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService service;

    @PostMapping
    public Expense createExpense (@RequestBody ExpenseDTO expenseDTO) {
        System.out.println(expenseDTO);
        if (expenseDTO.getUserId() == null) {
            throw new InvalidRequestException("The request body needs a userId field.");
        }
        if (expenseDTO.getCategoryId() == null) {
            throw new InvalidRequestException("The request body needs a categoryId field.");
        }
        return service.createExpense(expenseDTO);
    }

    @GetMapping
    public List<Expense> getAllExpenses () {
            return service.getALlExpenses();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Expense>> getUsersExpenses (@PathVariable int userId) {
        List<Expense> expenses = service.getUsersExpenses(userId);
        return ResponseEntity.ok(expenses);
    }


    @GetMapping("/{expId}")
    public ResponseEntity<Expense> getOneExpense (@PathVariable int expId) {
        return service.getOneExpense(expId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PatchMapping
    public ResponseEntity<Expense> updateExpense (@RequestParam int expenseId, @Validated @RequestBody ExpenseDTO expenseDTO) {

        Expense updatedExpense = service.updateExpense(expenseId, expenseDTO);
        if (updatedExpense.getId() != null) {
            return ResponseEntity.ok(updatedExpense);
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteExpense (@RequestParam int expenseId) {
        boolean deleted = service.deleteExpense(expenseId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }




}
