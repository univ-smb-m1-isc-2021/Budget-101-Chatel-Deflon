package budget.adapters;

import budget.adapters.request_template.EditBudgetForm;
import budget.adapters.request_template.RemoveByIdForm;
import budget.adapters.request_template.RmBudgetForm;
import budget.adapters.request_template.NewBudgetForm;
import budget.adapters.web.security.jwt.JwtTokenUtil;
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
    private JwtTokenUtil tokenUtil;
    private BudgetService budgetService;

    public BudgetController(BudgetService budgetService, JwtTokenUtil tokenUtil) {
        this.budgetService = budgetService;
        this.tokenUtil = tokenUtil;
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
        return budgetService.create(budgetForm.getName(), user.getId());
    }

    @PostMapping("/rmbudget")
    void rmBudget(@RequestBody RemoveByIdForm removeByIdForm) {
        budgetService.delete(removeByIdForm.getId());
    }

    @PostMapping("/editbudget")
    Budget editBudget(@RequestBody EditBudgetForm budgetForm){
        // Get concerned user
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return budgetService.edit(budgetForm.getId(), budgetForm.getName(), user.getId());
    }
}
