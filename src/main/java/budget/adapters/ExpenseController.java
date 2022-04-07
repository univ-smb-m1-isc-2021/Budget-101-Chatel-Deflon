package budget.adapters;

import budget.adapters.request_template.*;
import budget.application.ExpenseService;
import budget.persistence.budget.Budget;
import budget.persistence.expense.Expense;
import budget.persistence.expense.ExpenseRepetition;
import budget.persistence.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
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
        // Get concerned user
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return expenseService.expenses(user.getId());
    }

    @PostMapping("/newpuncexpense")
    Expense newPunctualExpense(@RequestBody NewPunctualExpenseForm expenseForm) {
        // Get concerned user
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return expenseService.createPunctualExpense(user.getId(), expenseForm.getLabel(), expenseForm.getAmount(), expenseForm.getBudgetId(), expenseForm.getDate());
    }

    @PostMapping("/newrecexpense")
    Expense newRecurrentExpense(@RequestBody NewRecurrentExpenseForm expenseForm) {
        // Get concerned user
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return expenseService.createRecurrentExpense(user.getId(), expenseForm.getLabel(), expenseForm.getAmount(), expenseForm.getBudgetId(), expenseForm.getDate(), expenseForm.getRepetition());
    }

    @PostMapping("/newsprexpense")
    Expense newSpreadExpense(@RequestBody NewSpreadExpenseForm expenseForm) {
        // Get concerned user
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return expenseService.createSpreadExpense(user.getId(), expenseForm.getLabel(), expenseForm.getAmount(), expenseForm.getBudgetId(), expenseForm.getStart(), expenseForm.getEnd());
    }

    @PostMapping("/rmexpense")
    void rmExpense(@RequestBody RemoveByIdForm removeByIdForm) {
        expenseService.delete(removeByIdForm.getId());
    }
}
