package com.codex.controller;


import com.codex.model.Category;
import com.codex.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
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
        Category cat = service.getOneCategory(catId);
        return ResponseEntity.ok(cat);
    }

    @PatchMapping
    public ResponseEntity<Category> updateCategory (@RequestParam int catId, @RequestBody Category category) {
        Category updatedCategory = service.updateCategory(catId, category);
        return ResponseEntity.ok(updatedCategory);
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
