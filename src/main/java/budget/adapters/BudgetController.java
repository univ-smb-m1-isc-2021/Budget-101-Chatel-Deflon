package budget.adapters;

import budget.adapters.request_template.EditBudgetForm;
import budget.adapters.request_template.RemoveByIdForm;

import budget.adapters.request_template.NewBudgetForm;
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
    private final BudgetService budgetService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping("/budgets")
    List<Budget> budgets() {
        // Get concerned user
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return budgetService.budgets(user.getId());
    }

    @PostMapping("/newbudget")
    Budget newBudget(@RequestBody NewBudgetForm budgetForm) {
        // Get concerned user
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return budgetService.create(new Budget(budgetForm.getName(), user.getId()));
    }

    @PostMapping("/rmbudget")
    void rmBudget(@RequestBody RemoveByIdForm removeByIdForm) {
        budgetService.delete(removeByIdForm.getId());
    }

    @PostMapping("/editbudget")
    Budget editBudget(@RequestBody EditBudgetForm budgetForm) {
        // Get concerned user
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return budgetService.edit(new Budget(budgetForm.getId(), budgetForm.getName(), user.getId()));
    }
}
