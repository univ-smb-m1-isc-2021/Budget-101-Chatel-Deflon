package budget.persistence.budget;

import budget.persistence.budget.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Budget findBudgetById(long id);
    List<Budget> findBudgetByUserId(long id);
    List<Budget> findBudgetByName(String name);
}
