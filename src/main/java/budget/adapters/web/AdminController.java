package budget.adapters.web;

import budget.application.BudgetService;
import budget.persistence.Budget;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class AdminController {

    private final BudgetService budgetService;

    public AdminController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping(value = "/admin")
    public String home(Model model) {
        model.addAttribute("budgets", budgetService.budgets());
        return "admin";
    }

    @PostMapping(value = "/admin/delete", params = {"budgetId"})
    public String removeRow(HttpServletRequest req) {
        Long budgetId = Long.valueOf(req.getParameter("budgetId"));
        budgetService.delete(budgetId);
        return "redirect:/admin";
    }

    @PostMapping("/admin/create/{name}")
    public String pidUserSubmit(@PathVariable("name") String name, Principal principal) {
        String author = principal.getName();
        budgetService.create(name);
        return "redirect:/admin";
    }
}