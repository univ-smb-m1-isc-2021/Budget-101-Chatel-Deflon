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

    public Expense createPunctualExpense(Long userId, String label, Float amount, Long budgetId, Date date) {
        return repository.save(new PunctualExpense(userId, label, amount, budgetId, date));
    }

    public Expense createRecurrentExpense(Long userId, String label, Float amount, Long budgetId, Date date, ExpenseRepetition repetition) {
        return repository.save(new RecurrentExpense(userId, label, amount, budgetId, date, repetition));
    }

    public Expense createSpreadExpense(Long userId, String label, Float amount, Long budgetId, Date start, Date end) {
        return repository.save(new SpreadExpense(userId, label, amount, budgetId, start, end));
    }
}
