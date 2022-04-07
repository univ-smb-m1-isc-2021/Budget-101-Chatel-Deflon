package budget.persistence.expense;

import budget.persistence.expense.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Expense findExpenseById(long id);

    List<Expense> findExpenseByLabel(String label);
}
