package c14.NoCountry.Service;

import c14.NoCountry.*;
import c14.NoCountry.Entity.Post;
import c14.NoCountry.Entity.Users;
import c14.NoCountry.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final JavaMailSender mailSender;

    @Autowired
    public UserService(UserRepository userRepository, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    public Users registerUser(Users user) throws Exception {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new Exception("El correo electrónico ya está registrado");
        }
        user.setPassword(encryptPassword(user.getPassword()));
        Users savedUser = userRepository.save(user);
        try {
            sendEmail(savedUser.getEmail(), "Registro Completo", "Registro Realizado Correctamente, Bienvenido");
        } catch (MailException e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
        return savedUser;
    }
    private String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("tstncntr@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);

        System.out.println("Mail Sent successfully");
    }


    public Users loginUser(String email, String password) {
        try {
            Users user = userRepository.findByEmail(email);
            if (user != null && passwordMatches(password, user.getPassword())) {
                user.getRole().getRole_name();
                return user;
            }
        } catch (Exception e) {
            System.err.println("Error al iniciar sesión: " + e.getMessage());
        }
        return null;
    }
    private boolean passwordMatches(String inputPassword, String storedPassword) {
        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedInputPassword = md.digest(inputPassword.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedInputPassword) {
                sb.append(String.format("%02x", b));
            }
            String hashedInputPasswordString = sb.toString();

            return hashedInputPasswordString.equals(storedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
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

    public void updateUser(Users user) {

        Users existingUser = userRepository.getById((long) user.getId());
        if (!existingUser.getPassword().equals(user.getPassword())) {
            try {
                user.setPassword(encryptPassword(user.getPassword()));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        userRepository.save(user);
    }

    public boolean updatePasswordByEmail(String email, String OldPasword, String newPassword, String confirmPassword) throws Exception {
        Users user = userRepository.findByEmail(email);
        if (user == null || !passwordMatches(OldPasword, user.getPassword())) {
            throw new Exception("Contraseña o correo no coincide, por favor intente de nuevo");

        }


        if (user == null) {
            throw new Exception("El usuario con el correo electrónico especificado no existe.");
        }


        if (!newPassword.equals(confirmPassword)) {
            throw new Exception("Las contraseñas no coinciden.");
        }


        String encryptedPassword = encryptPassword(newPassword);


        user.setPassword(encryptedPassword);
        userRepository.save(user);

        return true;
    }

    public List<Users> searchProjectByEmail(String searchTerm) {
        return userRepository.searchProjectByEmail(searchTerm);
    }

}