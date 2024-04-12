package c14.NoCountry.Service;

import c14.NoCountry.Entity.Role;
import c14.NoCountry.Entity.Users;
import c14.NoCountry.Repository.UserRepository;
import c14.NoCountry.dto.LoginRequestDto;
import c14.NoCountry.dto.UserAdminRegister;
import c14.NoCountry.dto.UserCreatorRegister;
import c14.NoCountry.dto.UserDonorRegister;
import c14.NoCountry.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public String registerUserDonor(UserDonorRegister userDonor) throws Exception {
        if (userRepository.existsByEmail(userDonor.getEmail())) {
            throw new Exception("El correo electrónico ya está registrado");
        }
        Users user = userMapper.userDonorToUser(userDonor);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Users savedUser = userRepository.save(user);
        sendEmailRegistration(savedUser.getEmail());
        return jwtService.getToken(savedUser);
    }

    public void sendEmailRegistration(String email){
        emailService.sendEmail(email, "Registro Completo", "Registro Realizado Correctamente, Bienvenido");
    }
    public String registerUserCreator(UserCreatorRegister userCreator) throws Exception {
        if (userRepository.existsByEmail(userCreator.getEmail())) {
            throw new Exception("El correo electrónico ya está registrado");
        }
        Users user = userMapper.userCreatorToUser(userCreator);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Users savedUser = userRepository.save(user);
        sendEmailRegistration(savedUser.getEmail());
        return jwtService.getToken(user);
    }
    public String registerUserAdmin(UserAdminRegister userAdmin) throws Exception {
        if (userRepository.existsByEmail(userAdmin.getEmail())) {
            throw new Exception("El correo electrónico ya está registrado");
        }
        Users user = userMapper.userAdminToUser(userAdmin);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Users savedUser = userRepository.save(user);
        sendEmailRegistration(savedUser.getEmail());
        return jwtService.getToken(savedUser);
    }
    public String login(LoginRequestDto loginRequestDto){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(),loginRequestDto.getPassword()));
        UserDetails user = userRepository.findByEmail(loginRequestDto.getEmail());
        return jwtService.getToken(user);
    }
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> getUserById(int id) {
        return userRepository.findById((long) id);
    }

    public void deleteUserById(int id) {
        userRepository.deleteById((long) id);
    }

    public void updateUser(Users user) throws Exception {
        Users existingUser = userRepository.findById((long) user.getId()).orElseThrow(()-> new Exception("User not found"));
        if (!existingUser.getPassword().equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    public boolean updatePasswordByEmail(String email, String OldPassword, String newPassword, String confirmPassword) throws Exception {
        Users user = userRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("El usuario con el correo electrónico especificado no existe.");
        }
        if (!passwordMatches(OldPassword, user.getPassword())) {
            throw new Exception("Contraseña no coincide, por favor intente de nuevo");
        }
        if (!newPassword.equals(confirmPassword)) {
            throw new Exception("Las contraseñas no coinciden.");
        }
        String encryptedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        return true;
    }

    private boolean passwordMatches(String oldPassword, String password) {
        String encodedPassword = passwordEncoder.encode(oldPassword);
        return encodedPassword.equals(password);
    }

    public List<Users> searchProjectByEmail(String searchTerm) {
        return userRepository.searchProjectByEmail(searchTerm);
    }
}