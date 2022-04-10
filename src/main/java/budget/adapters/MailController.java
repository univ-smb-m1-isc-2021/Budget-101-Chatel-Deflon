package budget.adapters;

import budget.application.BudgetService;
import budget.application.ExpenseService;
import budget.mail.EmailSenderService;
import budget.persistence.budget.Budget;
import budget.persistence.expense.Expense;
import budget.persistence.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MailController {
    @Autowired
    private EmailSenderService service;
    private BudgetService budgetService;
    private ExpenseService expenseService;

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

        String recap = "";
        for (int i = 0; i < budgets.size(); i++) {
            recap += (budgets.get(i).getName() + "\n");
            expenses = new ArrayList<>(expenseService.expensesOfBudget(budgets.get(i).getId()));
            for (int j = 0; j < expenses.size(); j++) {
                recap += (expenses.get(j).mailRecap());
            }
        }
        service.sendRecap(user, recap);
    }
}
