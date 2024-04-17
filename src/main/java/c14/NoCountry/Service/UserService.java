package c14.NoCountry.Service;

import c14.NoCountry.Entity.Role;
import c14.NoCountry.Entity.Users;
import c14.NoCountry.Repository.UserRepository;
import c14.NoCountry.dto.*;
import c14.NoCountry.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.Token;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public TokenResponse registerUserDonor(UserDonorRegister userDonor) throws Exception {
        if (userRepository.existsByEmail(userDonor.getEmail())) {
            throw new Exception("El correo electrónico ya está registrado");
        }
        Users user = userMapper.userDonorToUser(userDonor);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Users savedUser = userRepository.save(user);
        sendEmailRegistration(savedUser.getEmail());
        return TokenResponse.builder().token(jwtService.getToken(savedUser)).build();
    }

    public void sendEmailRegistration(String email){
        emailService.sendEmail(email, "Registro Completo", "Registro Realizado Correctamente, Bienvenido");
    }
    public TokenResponse registerUserCreator(UserCreatorRegister userCreator) throws Exception {
        if (userRepository.existsByEmail(userCreator.getEmail())) {
            throw new Exception("El correo electrónico ya está registrado");
        }
        Users user = userMapper.userCreatorToUser(userCreator);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Users savedUser = userRepository.save(user);
        sendEmailRegistration(savedUser.getEmail());
        return TokenResponse.builder().token(jwtService.getToken(user)).build();
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
    public TokenResponse login(LoginRequestDto loginRequestDto){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(),loginRequestDto.getPassword()));
        UserDetails user = userRepository.findByEmail(loginRequestDto.getEmail());
        return TokenResponse.builder().token(jwtService.getToken(user)).build();
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

    public Users getUserFromSecurityContextHolder(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof UsernamePasswordAuthenticationToken userToken)){
            return null;
        }
        UserDetails userDetails = (UserDetails) userToken.getPrincipal();
        return (Users) userDetails;
    }
}