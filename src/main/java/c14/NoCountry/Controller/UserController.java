package c14.NoCountry.Controller;

import c14.NoCountry.Service.UserService;
import c14.NoCountry.dto.*;
import c14.NoCountry.dto.user.*;
import c14.NoCountry.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @PostMapping("/registerDonor")
    public ResponseEntity<?> registerUserDonor(@RequestBody UserDonorRegister user) throws Exception {

        userService.validateField(user.getEmail(), "email is empty or null", "P - 401");
        userService.validateField(user.getPassword(), "password is empty or null", "P - 402");
        userService.validatePassword(user.getPassword());
        userService.validateField(user.getName(), "name is empty or null", "P - 403");
        userService.validateField(user.getLastname(), "last name is empty or null", "P - 404");
        return ResponseEntity.ok(userService.registerUserDonor(user));
    }

    @PostMapping("/registerCreator")
    public ResponseEntity<?> registerUserCreator(@RequestBody UserCreatorRegister user) throws Exception {
        userService.validateField(user.getEmail(), "email is empty or null", "P - 401");
        userService.validateField(user.getPassword(), "password is empty or null", "P - 402");
        userService.validatePassword(user.getPassword());
        userService.validateField(user.getName(), "name is empty or null", "P - 403");
        //userService.validateField(user.getLastname(), "last name is empty or null", "P - 404");
        userService.validateField(user.getPlace(), "name is empty or null", "P - 408");
        userService.validateField(user.getPhoto(), "name is empty or null", "P - 409");
        return ResponseEntity.ok(userService.registerUserCreator(user));
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<?> registerUserAdmin(@RequestBody UserAdminRegister user) throws Exception {
        userService.validateField(user.getEmail(), "email is empty or null", "P - 401");
        userService.validateField(user.getPassword(), "password is empty or null", "P - 402");
        userService.validatePassword(user.getPassword());
        userService.validateField(user.getName(), "name is empty or null", "P - 403");
        return ResponseEntity.ok(userService.registerUserAdmin(user));
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDto request) {
        userService.validateField(request.getEmail(), "email is empty or null", "P - 401");
        userService.validateField(request.getPassword(), "password is empty or null", "P - 402");

        return ResponseEntity.ok(userService.login(request));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUsers() {
        List<UserResponse> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") int id) throws UserException {
        UserResponse user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") int id) throws UserException {
        UserResponse user = userService.getUserById(id);
        userService.deleteUserById(id);
        return new ResponseEntity<>("Usuario eliminado correctamente", HttpStatus.NO_CONTENT);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody UserUpdateResponse user) throws Exception {
        UserResponse existingUser = userService.getUserById(id);
        userService.updateUser(user,id);
        return new ResponseEntity<>("Usuario actualizado correctamente", HttpStatus.OK);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<?> updatePasswordByEmail(@RequestBody UpdatePassword updatePassword) throws Exception {
        userService.validatePassword(updatePassword.getNewPassword());
        userService.updatePasswordByEmail(updatePassword);

        return new ResponseEntity<>("Password was updated successfully",HttpStatus.OK);

    }
    @GetMapping("/searchByEmail")
    public ResponseEntity<?> searchByEmail(@RequestParam("searchTerm") String searchTerm) {
        List<UserResponse> searchResult = userService.searchProjectByEmail(searchTerm);
        return ResponseEntity.ok(searchResult);
    }
    @GetMapping("/getCreators")
    public ResponseEntity<?> getAllCreators() {
        List<UserResponse> searchResult = userService.getAllCreators();
        return ResponseEntity.ok(searchResult);
    }
    @GetMapping("/getDonnors")
    public ResponseEntity<?> getAllDonnors() {
        List<UserResponse> searchResult = userService.getAllDonnors();
        return ResponseEntity.ok(searchResult);
    }
}
