package budget.adapters;

import budget.application.BudgetService;
import budget.application.ExpenseService;
import budget.mail.EmailSenderService;
import budget.persistence.budget.Budget;
import budget.persistence.expense.Expense;
import budget.persistence.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MailController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EmailSenderService service;
    private final BudgetService budgetService;
    private final ExpenseService expenseService;

    public MailController(BudgetService budgetService, ExpenseService expenseService) {
        this.budgetService = budgetService;
        this.expenseService = expenseService;
    }

    @GetMapping("/recapmail")
    void recapMail() {
        // Get concerned user
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        List<Budget> budgets = budgetService.budgets(user.getId());
        List<Expense> expenses;

        StringBuilder recap = new StringBuilder();
        for (Budget budget : budgets) {
            recap.append(budget.getName()).append("\n");
            expenses = new ArrayList<>(expenseService.expensesOfBudget(budget.getId()));
            for (Expense expens : expenses) {
                recap.append(expens.mailRecap());
            }
        }
        service.sendRecap(user, recap.toString());
    }
}
