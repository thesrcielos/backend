package c14.NoCountry.Service;

import c14.NoCountry.Entity.Users;
import c14.NoCountry.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Users registerUser(Users user) {
        return userRepository.save(user);
    }

    public Users loginUser(String username, String password) {
        Users user = userRepository.findByEmail(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}