package com.codex.service;


import com.codex.model.Expense;
import com.codex.repo.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepo repo;

    public List<Expense> getALlExpenses() {
        return repo.findAll();
    }


    public Optional<Expense> getOneExpense(int userId) {
        return repo.findById(userId);
    }


    public Expense createExpense(Expense expense) {
        return repo.save(expense);
    }

    public boolean deleteExpense(int expenseId) {

        if (repo.existsById(expenseId)) {
            repo.deleteById(expenseId);
            return true;
        }
        return false;

    }

    public boolean updateExpense(int expenseId, Expense newExpense) {

        Optional<Expense> optionalExpense = repo.findById(expenseId);
        if (optionalExpense.isPresent()) {

            Expense exp = optionalExpense.get();

            if (!newExpense.getDescription().isEmpty()) {
                exp.setDescription(newExpense.getDescription());
            }
            else if (newExpense.getAmount() != null) {
                exp.setAmount(newExpense.getAmount());
            }
            repo.save(exp);
            return true;
        }
        return false;
    }
}
