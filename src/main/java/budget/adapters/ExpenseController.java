package budget.adapters;

import budget.application.ExpenseService;
import budget.persistence.Expense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExpenseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/expenses")
    List<Expense> expenses() {
        return expenseService.expenses();
    }
}
