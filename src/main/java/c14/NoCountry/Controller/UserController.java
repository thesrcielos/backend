package c14.NoCountry.Controller;

import c14.NoCountry.Entity.Post;
import c14.NoCountry.Entity.Users;
import c14.NoCountry.Service.UserService;
import c14.NoCountry.dto.*;
import c14.NoCountry.exception.RequestException;
import c14.NoCountry.exception.UserException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @PostMapping("/registerDonor")
    public ResponseEntity<?> registerUserDonor(@RequestBody UserDonorRegister user) throws Exception {
        /*
        if(user.getEmail().equals("")|| user.getEmail() == null){
            //throw new RuntimeException("Email is invalid");
            throw new RequestException("P-401","Email is required");
        }
        if(user.getLastname().equals("")|| user.getLastname() == null){
            //throw new RuntimeException("Email is invalid");
            throw new RequestException("P-403","Last name is required");
        }*/
        userService.validateField(user.getEmail(), "email is empty or null", "P - 401");
        userService.validateField(user.getPassword(), "password is empty or null", "P - 402");
        userService.validateField(user.getName(), "name is empty or null", "P - 403");
        userService.validateField(user.getLastname(), "last name is empty or null", "P - 404");
        return ResponseEntity.ok(userService.registerUserDonor(user));
    }

    @PostMapping("/registerCreator")
    public ResponseEntity<?> registerUserCreator(@RequestBody UserCreatorRegister user) throws Exception {
        userService.validateField(user.getEmail(), "email is empty or null", "P - 401");
        userService.validateField(user.getPassword(), "password is empty or null", "P - 402");
        userService.validateField(user.getName(), "name is empty or null", "P - 403");
        userService.validateField(user.getPlace(), "place is empty or null", "P - 408");
        userService.validateField(user.getPhoto(), "photo is empty or null", "P - 409");

        //userService.validateField(user.getLastname(), "last name is empty or null", "P - 404");
        return ResponseEntity.ok(userService.registerUserCreator(user));
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<?> registerUserAdmin(@RequestBody UserAdminRegister user) throws Exception {
        userService.validateField(user.getEmail(), "email is empty or null", "P - 401");
        userService.validateField(user.getPassword(), "password is empty or null", "P - 402");
        userService.validateField(user.getName(), "name is empty or null", "P - 403");
        return ResponseEntity.ok(userService.registerUserAdmin(user));
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDto request) {
        userService.validateField(request.getEmail(), "email is empty or null", "P - 401");
        userService.validatePassword(request.getPassword());
        return ResponseEntity.ok(userService.login(request));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
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
    public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody Users user) throws Exception {
        UserResponse existingUser = userService.getUserById(id);
        user.setId(id); // asegurarse de que el ID coincida
        userService.updateUser(user);
        return new ResponseEntity<>("Usuario actualizado correctamente", HttpStatus.OK);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<String> updatePasswordByEmail(@RequestParam String email,@RequestParam String OldPassword,
                                                        @RequestParam String newPassword,
                                                        @RequestParam String confirmPassword) {
        try {
            userService.validatePassword(newPassword);
            boolean passwordUpdated = userService.updatePasswordByEmail(email,OldPassword, newPassword, confirmPassword);
            if (passwordUpdated) {
                return ResponseEntity.ok("¡La contraseña se ha actualizado correctamente!");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("La contraseña no se pudo actualizar.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar la contraseña: " + e.getMessage());
        }
    }
    @GetMapping("/searchByEmail")
    public ResponseEntity<?> searchByEmail(@RequestParam("searchEmail") String searchTerm) {
        List<UserResponse> searchResult = userService.searchProjectByEmail(searchTerm);
        return ResponseEntity.ok(searchResult);
    }
}
