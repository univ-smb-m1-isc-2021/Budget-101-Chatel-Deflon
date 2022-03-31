package budget.persistence;

import budget.application.BudgetService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Initializer {

    private final TestRepository repository;
    private final BudgetRepository budgetRepository;

    public Initializer(TestRepository repository, BudgetRepository budgetRepository) {
        this.repository = repository;
        this.budgetRepository = budgetRepository;
    }

    @PostConstruct
    public void initialize() {

        repository.deleteAllInBatch();

        if (repository.findAll().isEmpty()) {
            repository.saveAndFlush(new Test("Test", "Description Test"));
            repository.saveAndFlush(new Test("Test1", "Le TOUT premier test"));
            repository.saveAndFlush(new Test("Test2", "Oui bonsoir c'est le test 2"));
            repository.saveAndFlush(new Test("Test3", "Le test 3, c'est plus fort que toi"));
            repository.saveAndFlush(new Test("Test4", "Le test 4, est meilleurs que toi aux cartes"));
            repository.saveAndFlush(new Test("Test5"));
        }

        if (budgetRepository.findAll().isEmpty()) {
            budgetRepository.saveAndFlush(new Budget("Voiture"));
            budgetRepository.saveAndFlush(new Budget("Courses"));
            budgetRepository.saveAndFlush(new Budget("Rénovations"));
            budgetRepository.saveAndFlush(new Budget("Vacances"));
        }
    }
}
