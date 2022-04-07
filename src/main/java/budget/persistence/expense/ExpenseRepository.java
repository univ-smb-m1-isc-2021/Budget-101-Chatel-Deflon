package budget.persistence.expense;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Expense findExpenseById(Long id);

    List<Expense> findExpensesByUserId(Long userId);

    List<Expense> findExpensesByLabel(String label);

    List<Expense> findExpensesByBudgetId(Long budgetId);

    List<Expense> findExpensesByType(ExpenseType expenseType);

    List<Expense> findExpensesByUserIdAndBudgetId(Long userId, Long budgetId);

    List<Expense> findExpensesByUserIdAndType(Long userId, ExpenseType expenseType);
}
