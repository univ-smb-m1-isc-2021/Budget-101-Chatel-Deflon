package budget.adapters;

import budget.adapters.request_template.*;
import budget.application.ExpenseService;
import budget.persistence.expense.*;
import budget.persistence.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExpenseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ExpenseService expenseService;

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
        return expenseService.create(new PunctualExpense(user.getId(), expenseForm.getLabel(), expenseForm.getAmount(), expenseForm.getBudgetId(), expenseForm.getDate()));
    }

    @PostMapping("editpuncexpense")
    Expense editPunctualExpense(@RequestBody EditPunctualExpenseForm expenseForm) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return expenseService.edit(new PunctualExpense(expenseForm.getId(), user.getId(), expenseForm.getLabel(), expenseForm.getAmount(), expenseForm.getBudgetId(), expenseForm.getDate()));
    }

    @PostMapping("/newrecexpense")
    Expense newRecurrentExpense(@RequestBody NewRecurrentExpenseForm expenseForm) {
        // Get concerned user
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return expenseService.create(new RecurrentExpense(user.getId(), expenseForm.getLabel(), expenseForm.getAmount(), expenseForm.getBudgetId(), expenseForm.getDate(), expenseForm.getRepetition()));
    }

    @PostMapping("editrecexpense")
    Expense editRecExpense(@RequestBody EditRecurrentExpenseForm expenseForm) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return expenseService.edit(new RecurrentExpense(expenseForm.getId(), user.getId(), expenseForm.getLabel(), expenseForm.getAmount(), expenseForm.getBudgetId(), expenseForm.getDate(), expenseForm.getRepetition()));
    }

    @PostMapping("/newsprexpense")
    Expense newSpreadExpense(@RequestBody NewSpreadExpenseForm expenseForm) {
        // Get concerned user
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return expenseService.create(new SpreadExpense(user.getId(), expenseForm.getLabel(), expenseForm.getAmount(), expenseForm.getBudgetId(), expenseForm.getStart(), expenseForm.getEnd()));
    }

    @PostMapping("/editsprexpense")
    Expense editSpreadExpense(@RequestBody EditSpreadExpenseForm expenseForm) {
        // Get concerned user
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return expenseService.edit(new SpreadExpense(expenseForm.getId(), user.getId(), expenseForm.getLabel(), expenseForm.getAmount(), expenseForm.getBudgetId(), expenseForm.getStart(), expenseForm.getEnd()));
    }

    @PostMapping("/rmexpense")
    void rmExpense(@RequestBody RemoveByIdForm removeByIdForm) {
        expenseService.delete(removeByIdForm.getId());
    }
}
