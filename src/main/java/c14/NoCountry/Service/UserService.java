package c14.NoCountry.Service;

import c14.NoCountry.Entity.Users;
import c14.NoCountry.Repository.UserRepository;
import c14.NoCountry.dto.PostResponse;
import c14.NoCountry.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Users registerUser(Users user) {
        return userRepository.save(user);
    }
    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }
    public List<UserResponse> findByAll(){
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    public Users loginUser(String username, String password) {
        Users user = userRepository.findByEmail(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }


}