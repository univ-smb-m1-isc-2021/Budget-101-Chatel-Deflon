package budget.application;

import budget.persistence.budget.Budget;
import budget.persistence.budget.BudgetRepository;
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

    public List<Budget> budgets(Long userId) {
        return this.repository.findBudgetByUserId(userId);
    }

    public Budget create(Budget budget) {
        return repository.saveAndFlush(budget);
    }

    public void delete(Long budgetId) {
        Optional<Budget> budget = repository.findById(budgetId);
        budget.ifPresent(repository::delete);
    }

    public Budget edit(Budget budget) {
        return repository.saveAndFlush(budget);
    }
}