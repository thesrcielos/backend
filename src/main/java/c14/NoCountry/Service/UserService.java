package c14.NoCountry.Service;

import c14.NoCountry.Entity.Users;
import c14.NoCountry.Repository.UserRepository;
import c14.NoCountry.dto.*;
import c14.NoCountry.exception.UserException;
import c14.NoCountry.jwt.JwtService;
import lombok.RequiredArgsConstructor;
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

    public List<UserResponse> searchProjectByEmail(String searchTerm) {
        return userRepository.searchProjectByEmail(searchTerm).stream()
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
}