package budget.adapters;

import budget.application.BudgetService;
import budget.persistence.budget.Budget;
import budget.persistence.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BudgetController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }
    @CrossOrigin(origins="http://localhost:4200/", maxAge=3600)
    @GetMapping("/budgets")
    List<Budget> budgets() {
        return budgetService.budgets();
    }

    @GetMapping("/newbudget/{name}")
    Budget newBudget(@PathVariable("name") String name) {
        return budgetService.create(name);
    }

    @GetMapping("/rmbudget/{id}")
    void newBudget(@PathVariable("id") Long id) {
        budgetService.delete(id);
    }

    @GetMapping("/api/user")
    public String getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof User) {
            username = ((User)principal).toString();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
