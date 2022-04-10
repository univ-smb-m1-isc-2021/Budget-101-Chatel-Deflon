package budget.adapters;

import budget.adapters.request_template.ModifUserForm;
import budget.adapters.request_template.NewUserForm;
import budget.adapters.request_template.UserForm;
import budget.adapters.web.security.SecurityConfig;
import budget.application.UserService;
import budget.persistence.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class UserController {
    private final UserService userService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    UserForm getUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return new UserForm(user.getUsername(), user.getEmail());
    }

    @PostMapping("/register")
    Boolean newUser(@RequestBody NewUserForm userForm) {
        User user = userService.create(userForm.getUsername(), new BCryptPasswordEncoder().encode(userForm.getPassword()), userForm.getEmail());
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

    @PostMapping("removeuser")
    void removeUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
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
