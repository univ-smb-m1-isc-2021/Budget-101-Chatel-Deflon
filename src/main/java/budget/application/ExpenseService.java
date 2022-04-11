package budget.application;

import budget.persistence.expense.ExpenseRepetition;
import budget.persistence.expense.*;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    private final ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    public List<Expense> expenses() {
        return this.repository.findAll();
    }

    public List<Expense> expenses(Long userId) {
        return this.repository.findExpensesByUserId(userId);
    }

    public List<Expense> expensesOfBudget(Long budgetId) {
        return this.repository.findExpensesByBudgetId(budgetId);
    }

    public void delete(Long expenseId) {
        Optional<Expense> expense = repository.findById(expenseId);
        expense.ifPresent(repository::delete);
    }

    public Expense create(Expense expense) {
        return repository.saveAndFlush(expense);
    }

    public Expense edit(Expense expense) {
        return repository.saveAndFlush(expense);
    }
}
