package c14.NoCountry.Service;

import c14.NoCountry.Entity.users;
import c14.NoCountry.Repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public users registerUser(users user) {
        return userRepository.save(user);
    }

    public users loginUser(String username, String password) {
        users user = userRepository.findByEmail(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}