package budget.adapters;

import budget.adapters.request_template.NewUserForm;
import budget.adapters.request_template.UserForm;
import budget.application.BudgetService;
import budget.application.ExpenseService;
import budget.application.UserService;
import budget.persistence.budget.Budget;
import budget.persistence.expense.Expense;
import budget.persistence.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    private final BudgetService budgetService;
    private final ExpenseService expenseService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserController(UserService userService, BudgetService budgetService, ExpenseService expenseService) {
        this.userService = userService;
        this.budgetService = budgetService;
        this.expenseService = expenseService;
    }

    @GetMapping("/user")
    UserForm getUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return new UserForm(user.getUsername(), user.getEmail());
    }

    @PostMapping("/register")
    Boolean newUser(@RequestBody NewUserForm userForm) {
        User user = userService.create(new User(userForm.getUsername(), new BCryptPasswordEncoder().encode(userForm.getPassword()), userForm.getEmail()));
        return user != null;
    }

    @PostMapping("/editmail")
    UserForm editMail(@RequestBody UserForm userForm) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        user.setEmail(userForm.getEmail());
        userService.edit(user);
        return new UserForm(user.getUsername(), user.getEmail());
    }

    @GetMapping("removeuser")
    void removeUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        // Suppression des données utilisateur
        List<Budget> tmp = budgetService.budgets(user.getId());
        for (Budget budget : tmp) {
            budgetService.delete(budget.getId());
        }
        List<Expense> tmpExp = expenseService.expenses(user.getId());
        for (Expense expense : tmpExp) {
            expenseService.delete(expense.getId());
        }
        // Suppression de l'utilisateur
        userService.delete(user.getId());
    }

//  Annulé car multipes soucis au niveau de la réauthentification via jwt (on a plus le temsp :') )
//    @PostMapping("/edituser")
//    UserForm editUser(@RequestBody ModifUserForm userForm) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal();
//
//        PasswordEncoder crypt = SecurityConfig.passwordEncoder();
//
//        System.out.println("User controller");
//        System.out.println(user);
//
//        // Check password absent car je n'arrivais pas a retomber sur la même encryption ou à authentifier via l'auth provider.
//        user.setUsername(userForm.getUsername());   // username changes
//        if (!Objects.equals(userForm.getNewPassword(), "") && !Objects.equals(crypt.encode(userForm.getNewPassword()), user.getPassword()))
//            user.setPassword(crypt.encode(userForm.getPassword()));
//        user.setEmail(userForm.getEmail());         // email changes
//        userService.edit(user);
//        User tmp = userService.userById(user.getId());
//        System.out.println(tmp);
//        return new UserForm(user.getUsername(), user.getEmail());
//    }
}
