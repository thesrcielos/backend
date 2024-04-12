package c14.NoCountry.Controller;

import c14.NoCountry.Entity.Post;
import c14.NoCountry.Entity.Users;
import c14.NoCountry.Service.UserService;
import c14.NoCountry.dto.LoginRequestDto;
import c14.NoCountry.dto.UserAdminRegister;
import c14.NoCountry.dto.UserCreatorRegister;
import c14.NoCountry.dto.UserDonorRegister;
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
public class UserController {

    private final UserService userService;

    @PostMapping("/registerDonor")
    public ResponseEntity<?> registerUserDonor(@RequestBody UserDonorRegister user) throws Exception {
        return ResponseEntity.ok(userService.registerUserDonor(user));
    }

    @PostMapping("/registerCreator")
    public ResponseEntity<?> registerUserCreator(@RequestBody UserCreatorRegister user) throws Exception {
        return ResponseEntity.ok(userService.registerUserCreator(user));
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<?> registerUserAdmin(@RequestBody UserAdminRegister user) throws Exception {
        return ResponseEntity.ok(userService.registerUserAdmin(user));
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(userService.login(request));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
        Optional<Users> user = userService.getUserById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") int id) {
        Optional<Users> user = userService.getUserById(id);
        if (user.isPresent()) {
            userService.deleteUserById(id);
            return new ResponseEntity<>("Usuario eliminado correctamente", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody Users user) throws Exception {
        Optional<Users> existingUser = userService.getUserById(id);
        if (existingUser.isPresent()) {
            user.setId(id); // asegurarse de que el ID coincida
            userService.updateUser(user);
            return new ResponseEntity<>("Usuario actualizado correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<String> updatePasswordByEmail(@RequestParam String email,@RequestParam String OldPassword,
                                                        @RequestParam String newPassword,
                                                        @RequestParam String confirmPassword) {
        try {
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
    @GetMapping("/searchByName")
    public ResponseEntity<?> searchByEmail(@RequestParam("searchTerm") String searchTerm) {
        List<Users> searchResult = userService.searchProjectByEmail(searchTerm);
        return ResponseEntity.ok(searchResult);
    }
}
