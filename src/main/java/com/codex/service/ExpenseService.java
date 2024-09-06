package com.codex.service;


import com.codex.DTO.ExpenseDTO;
import com.codex.exceptions.CategoryNotFoundException;
import com.codex.exceptions.ExpenseNotFoundException;
import com.codex.exceptions.UserNotFoundException;
import com.codex.model.Category;
import com.codex.model.Expense;
import com.codex.model.User;
import com.codex.repo.CategoryRepo;
import com.codex.repo.ExpenseRepo;
import com.codex.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepo repo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo catRepo;

    public List<Expense> getALlExpenses() {
        return repo.findAll();
    }


    public Optional<Expense> getOneExpense(long userId) {
        return repo.findById(userId);
    }


    public Expense createExpense(@RequestBody ExpenseDTO expenseDTO) {

        Expense expense = new Expense();
        expense.setAmount(expenseDTO.getAmount());
        expense.setDescription(expenseDTO.getDescription());

        if (userRepo.existsById(expenseDTO.getUserId())) {
            Optional<User> optionalUser = userRepo.findById(expenseDTO.getUserId());
            if (optionalUser.isEmpty()) {
                throw new UserNotFoundException();
            }
            expense.setUser(optionalUser.get());
        }

        if ( catRepo.existsById(expenseDTO.getCategoryId()) ) {
            Optional<Category> optionalCategory = catRepo.findById(expenseDTO.getCategoryId());
            if (optionalCategory.isEmpty()) {
                throw new CategoryNotFoundException();
            }
            expense.setCategory(optionalCategory.get());
        }

        return repo.save(expense);
    }




    public boolean deleteExpense(long expenseId) {

        if (repo.existsById(expenseId)) {
            repo.deleteById(expenseId);
            return true;
        }
        return false;

    }

    public Expense updateExpense(long expenseId, ExpenseDTO expenseDTO) {

        Optional<Expense> optionalExpense = repo.findById(expenseId);

        if (optionalExpense.isEmpty()) {
            throw new ExpenseNotFoundException();
        }

        Expense exp = optionalExpense.get();

        // check for category
        Optional<Category> category = catRepo.findById(expenseDTO.getCategoryId());
        if (category.isEmpty()) {
            throw new CategoryNotFoundException();
        }
        exp.setCategory(category.get());

        if (!expenseDTO.getDescription().isEmpty()) {
            exp.setDescription(expenseDTO.getDescription());
        }
        if (expenseDTO.getAmount() != null) {
            exp.setAmount(expenseDTO.getAmount());
        }

        repo.save(exp);

        return exp;
    }
}
