package budget.persistence;

import budget.adapters.web.security.SecurityConfig;
import budget.mail.EmailSenderService;
import budget.persistence.budget.Budget;
import budget.persistence.budget.BudgetRepository;
import budget.persistence.expense.Expense;
import budget.persistence.expense.ExpenseRepository;
import budget.persistence.expense.PunctualExpense;
import budget.persistence.user.User;
import budget.persistence.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class Initializer {
    private final BudgetRepository budgetRepository;
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;



    public Initializer(BudgetRepository budgetRepository, ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.budgetRepository = budgetRepository;
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initialize() {
        budgetRepository.deleteAll();
        expenseRepository.deleteAll();
        userRepository.deleteAll();

        if(userRepository.findAll().isEmpty()){
            userRepository.saveAndFlush(new User("root",  SecurityConfig.passwordEncoder().encode("root"), "platinetwitch@gmail.com"));
            userRepository.saveAndFlush(new User("foo",  SecurityConfig.passwordEncoder().encode("foo"), "root@root.fr"));
        }

        Long idRoot = userRepository.findUserByUsername("root").getId();
        Long idFoot = userRepository.findUserByUsername("foo").getId();

        if (budgetRepository.findAll().isEmpty()) {
            budgetRepository.saveAndFlush(new Budget("Voiture",idFoot));
            budgetRepository.saveAndFlush(new Budget("Courses",idRoot));
            budgetRepository.saveAndFlush(new Budget("Rénovations",idRoot));
            budgetRepository.saveAndFlush(new Budget("Vacances",idRoot));

        }
        if (expenseRepository.findAll().isEmpty()) {
            expenseRepository.saveAndFlush(new PunctualExpense(idRoot,"depenseBouffe", 200.0f, budgetRepository.findBudgetByName("Courses").get(0).getId(), new Date(System.currentTimeMillis())));
        }
    }
}