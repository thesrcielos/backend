package c14.NoCountry.Controller;

import c14.NoCountry.Entity.users;
import c14.NoCountry.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public users registerUser(@RequestBody users user) {

        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public users loginUser(@RequestParam String username, @RequestParam String password) {
        return userService.loginUser(username, password);
    }
}
