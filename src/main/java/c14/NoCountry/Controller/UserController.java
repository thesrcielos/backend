package c14.NoCountry.Controller;

import c14.NoCountry.Entity.Post;
import c14.NoCountry.Entity.Users;
import c14.NoCountry.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public Users registerUser(@RequestBody Users user) {

        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public Users loginUser(@RequestParam String username, @RequestParam String password) {
        return userService.loginUser(username, password);
    }
    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@Valid @RequestBody Users users){
        return ResponseEntity.ok(userService.registerUser(users));
    }
    @GetMapping("/all-users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.findByAll());
    }


    @DeleteMapping("deletePost/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
