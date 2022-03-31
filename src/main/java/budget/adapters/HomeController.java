package budget.adapters;

import budget.application.BudgetService;
import budget.application.TestService;
import budget.application.BudgetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
public class HomeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final TestService testService;
    private final BudgetService budgetService;

    public HomeController(TestService testService, BudgetService budgetService) {
        this.testService = testService;
        this.budgetService = budgetService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/api/test")
    public List<String> facts() {
        logger.info("Serving Facts");
        return testService.tests()
                .stream()
                .map(p -> p.getNom())
                .collect(toList());
    }

    @GetMapping(value = "api/budgets")
    public List<String> budgets() {
        logger.info("Serving Budgets");
        return budgetService.budgets()
                .stream()
                .map(p -> p.getName())
                .collect(toList());
    }
}
