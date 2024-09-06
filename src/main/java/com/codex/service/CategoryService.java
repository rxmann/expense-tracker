package com.codex.service;


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


    public Optional<Category> getOneCategory(long catId) {
        return repo.findById(catId);
    }

    public Category createCategory(Category category) {
        return repo.save(category);
    }

    public boolean deleteExpense(long catId) {

        if (repo.existsById(catId)) {
            repo.deleteById(catId);
            return true;
        }
        return false;

    }

    public boolean updateCategory(long catId, Category newCat) {

        Optional<Category> optionalCategory = repo.findById(catId);
        if (optionalCategory.isPresent()) {

            Category exp = optionalCategory.get();

            repo.save(exp);
            return true;
        }
        return false;
    }
}
