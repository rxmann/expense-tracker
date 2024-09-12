package com.codex.service;


import com.codex.exceptions.CategoryNotFoundException;
import com.codex.model.Category;
import com.codex.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo repo;

    public List<Category> getALlCategories () {
        return repo.findAll();
    }


    public Category getOneCategory(long catId) {
        Optional<Category> optionalCategory = repo.findById(catId);
        if (optionalCategory.isEmpty()) {
            throw new CategoryNotFoundException();
        }
        return optionalCategory.get();
    }

    public Category createCategory(Category category) {
        System.out.println(category);
        return repo.save(category);
    }

    public boolean deleteExpense(long catId) {

        if (repo.existsById(catId)) {
            repo.deleteById(catId);
            return true;
        }
        return false;

    }

    public Category updateCategory(long catId, Category newCat) {

        Optional<Category> optionalCategory = repo.findById(catId);
        if (optionalCategory.isEmpty()) {
            throw new CategoryNotFoundException();
        }
        Category cat = optionalCategory.get();

        if (newCat.getName() != null) {
            cat.setName(newCat.getName());
        }

        return repo.save(cat);
    }
}
