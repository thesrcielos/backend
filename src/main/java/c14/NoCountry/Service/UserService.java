package c14.NoCountry.Service;

import c14.NoCountry.Entity.Users;
import c14.NoCountry.Repository.UserRepository;
import c14.NoCountry.dto.*;
import c14.NoCountry.dto.user.*;
import c14.NoCountry.exception.RequestException;
import c14.NoCountry.exception.UserException;
import c14.NoCountry.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
    public TokenResponse registerUserDonor(UserDonorRegister userDonor) throws UserException{
        if (userRepository.existsByEmail(userDonor.getEmail())) {
            throw new UserException(UserException.REGISTERED_EMAIL);
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
    public TokenResponse registerUserCreator(UserCreatorRegister userCreator) throws UserException {
        if (userRepository.existsByEmail(userCreator.getEmail())) {
            throw new UserException(UserException.REGISTERED_EMAIL);
        }
        Users user = userMapper.userCreatorToUser(userCreator);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Users savedUser = userRepository.save(user);
        sendEmailRegistration(savedUser.getEmail());
        return TokenResponse.builder().token(jwtService.getToken(user)).build();
    }
    public TokenResponse registerUserAdmin(UserAdminRegister userAdmin) throws Exception {
        if (userRepository.existsByEmail(userAdmin.getEmail())) {
            throw new UserException(UserException.REGISTERED_EMAIL);
        }
        Users user = userMapper.userAdminToUser(userAdmin);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Users savedUser = userRepository.save(user);
        sendEmailRegistration(savedUser.getEmail());
        return TokenResponse.builder().token(jwtService.getToken(savedUser)).build();
    }
    public TokenResponse login(LoginRequestDto loginRequestDto){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(),loginRequestDto.getPassword()));
        UserDetails user = userRepository.findByEmail(loginRequestDto.getEmail());
        return TokenResponse.builder().token(jwtService.getToken(user)).build();
    }
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    public UserResponse getUserById(int id) throws UserException {
        Optional<Users> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserException(UserException.USER_NOT_FOUND);
        }
        return userMapper.toUserResponse(user.get());
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    public void updateUser(UserUpdateResponse user, int id) throws Exception {
        Users existingUser = userRepository.findById(id).orElseThrow(()-> new UserException(UserException.USER_NOT_FOUND));
        existingUser.setName(user.getName());
        existingUser.setLastname(user.getLastname());
        existingUser.setRrs_fb(user.getRrs_fb());
        existingUser.setRrs_ig(user.getRrs_ig());
        existingUser.setPlace(user.getPlace());
        existingUser.setPhoto(user.getPhoto());
        userRepository.save(existingUser);
    }

    public void updatePasswordByEmail(UpdatePassword updatePassword) throws Exception {
        Users user = userRepository.findByEmail(updatePassword.getEmail());
        if (user == null) {
            throw new Exception("El usuario con el correo electrónico especificado no existe.");
        }
        if (!passwordMatches(updatePassword.getOldPassword(), user.getPassword())) {
            throw new Exception("Contraseña no coincide, por favor intente de nuevo");
        }
        String encryptedPassword = passwordEncoder.encode(updatePassword.getNewPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
    }

    private boolean passwordMatches(String oldPassword, String password) {
        return BCrypt.checkpw(oldPassword,password);
    }

    public List<UserResponse> searchProjectByEmail(String searchTerm) {
        return userRepository.searchProjectByEmail(searchTerm).stream()
                .map(userMapper::toUserResponse).toList();
    }

    public List<UserResponse> getAllCreators() {
        return userRepository.getAllCreator().stream()
                .map(userMapper::toUserResponse).toList();
    }
    public List<UserResponse> getAllDonnors() {
        return userRepository.getAllDonnor().stream()
                .map(userMapper::toUserResponse).toList();
    }

    public Users getUserFromSecurityContextHolder(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof UsernamePasswordAuthenticationToken userToken)){
            return null;
        }
        UserDetails userDetails = (UserDetails) userToken.getPrincipal();
        return (Users) userDetails;
    }

    //Metodos Genericos para el manejo de excepciones:
    //Metodo para campos nulos o vacios
    public void validateField(String field, String fieldName, String errorCode) {
        if (field == null || field.isEmpty()) {
            throw new RequestException(errorCode, fieldName + " is required");
        }
    }
    //Validacion para el password
    public void validatePassword(String password) {
        if (password.length() < 8) {
            throw new RequestException("P-461", "Password must be at least 8 characters long");
        }

        if (!password.matches(".*[!@#$%^&*()_+=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            throw new RequestException("P-461", "Password must contain at least one special character");
        }

        if (!password.matches(".*\\d.*")) {
            throw new RequestException("P-461", "Password must contain at least one digit");
        }

        if (!password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*")) {
            throw new RequestException("P-461", "Password must contain at least one uppercase and one lowercase letter");
        }
    }

}