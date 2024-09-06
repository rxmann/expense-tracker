package com.codex.controller;


import com.codex.model.Category;
import com.codex.model.Expense;
import com.codex.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping
    public Category createCategory (@Validated @RequestBody Category category) {
        return service.createCategory(category);
    }

    @GetMapping
    public List<Category> getAllCategories () {
        return service.getALlCategories();
    }

    @GetMapping("/{catId}")
    public ResponseEntity<Category> getOneCategory (@PathVariable int catId) {
        return service.getOneCategory(catId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PatchMapping
    public ResponseEntity<Category> updateCategory (@RequestParam int expenseId, @RequestBody Category category) {
        boolean updated = service.updateCategory(expenseId, category);
        if (updated) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteExpense (@RequestParam int catId) {
        boolean deleted = service.deleteExpense(catId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }




}
