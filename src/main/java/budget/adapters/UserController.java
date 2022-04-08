package budget.adapters;

import budget.adapters.request_template.NewUserForm;
import budget.adapters.request_template.UserForm;
import budget.application.UserService;
import budget.persistence.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user")
    UserForm getUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        UserForm tmp = new UserForm(user.getUsername(),user.getEmail());
        System.out.println(tmp);
        return tmp;
    }

    @PostMapping("/register")
    Boolean newUser(@RequestBody NewUserForm userForm){
        User user = userService.create(userForm.getUsername(), new BCryptPasswordEncoder().encode(userForm.getPassword()), userForm.getEmail());
        return user != null;
    }

}
