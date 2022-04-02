package budget.adapters;

import budget.application.BudgetService;
import budget.persistence.Budget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BudgetController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private BudgetService budgetService;

    public BudgetController(BudgetService budgetService){
        this.budgetService = budgetService;
    }
    @GetMapping("/budgets")
    List<Budget> budgets() {
        return budgetService.budgets();
    }
}
