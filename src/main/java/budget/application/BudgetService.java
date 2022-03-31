package budget.application;

import budget.persistence.Budget;
import budget.persistence.BudgetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {
    private final BudgetRepository repository;

    public BudgetService(BudgetRepository repository) {
        this.repository = repository;
    }

    public List<Budget> budgets() {
        return this.repository.findAll();
    }

    public void delete(Long budgetId) {
        Optional<Budget> budget = repository.findById(budgetId);
        budget.ifPresent(repository::delete);
    }

    public void create(String name) {
        repository.save(new Budget(name));
    }
}