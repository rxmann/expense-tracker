package com.codex.controller;


import com.codex.model.Expense;
import com.codex.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService service;

    @PostMapping
    public Expense createExpense (@Validated @RequestBody Expense expense) {
        System.out.println(expense);
        return service.createExpense(expense);
    }

    @GetMapping
    public List<Expense> getAllExpenses () {
            return service.getALlExpenses();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Expense> getOneExpense (@PathVariable int userId) {
        return service.getOneExpense(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PatchMapping
    public ResponseEntity<Expense> updateExpense (@RequestParam int expenseId, @RequestBody Expense expense) {
        boolean updated = service.updateExpense(expenseId, expense);
        if (updated) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
