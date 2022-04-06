package budget.persistence;

import budget.application.BudgetService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Date;

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
        if (budgetRepository.findAll().isEmpty()) {
            budgetRepository.saveAndFlush(new Budget("Voiture"));
            budgetRepository.saveAndFlush(new Budget("Courses"));
            budgetRepository.saveAndFlush(new Budget("Rénovations"));
            budgetRepository.saveAndFlush(new Budget("Vacances"));
        }
        if (expenseRepository.findAll().isEmpty()) {
            expenseRepository.saveAndFlush(new PunctualExpense("DepenseVoiture", 200.0f, budgetRepository.findAll().get(0).getId(), new Date(System.currentTimeMillis())));
        }

        if(userRepository.findAll().isEmpty()){
            userRepository.saveAndFlush(new User("root", "root", "root@root.fr"));
        }
    }
}
