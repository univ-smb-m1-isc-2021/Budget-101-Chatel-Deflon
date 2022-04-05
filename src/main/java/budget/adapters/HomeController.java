package budget.adapters;

import budget.application.BudgetService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;

import static org.slf4j.LoggerFactory.getLogger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class HomeController {
    private final Logger log = getLogger(this.getClass());
    private final BudgetService budgetService;

    public HomeController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("budgets", budgetService.budgets());
        log.info("Home page returned - testing logstash integration");
        return "home";
    }
}
