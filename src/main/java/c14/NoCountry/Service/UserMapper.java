package c14.NoCountry.Service;

import c14.NoCountry.Entity.Post;
import c14.NoCountry.Entity.Users;
import c14.NoCountry.dto.PostResponse;
import c14.NoCountry.dto.PostUpdateResponse;
import c14.NoCountry.dto.UserResponse;
import c14.NoCountry.dto.UserUpdateResponse;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserResponse toUserResponse(Users users){
    if(users==null){
        throw new NullPointerException("User cant be null");
    }
        return UserResponse.builder()
                //.id(post.getId())
                .email(users.getEmail())
                .name(users.getName())
                .lastname(users.getLastname())
                .password(users.getPassword())
                .rrs_fb(users.getRrs_fb())
                .rrs_ig(users.getRrs_ig())
                .place(users.getPlace())
                .photo(users.getPhoto())
                .build();
    }
    public UserUpdateResponse toUserUpdateResponse(Users users){
        if(users==null){
            throw new NullPointerException("User cant be null");
        }
        return UserUpdateResponse.builder()
                .id(users.getId())
                .name(users.getName())
                .lastname(users.getLastname())
                .password(users.getPassword())
                .rrs_fb(users.getRrs_fb())
                .rrs_ig(users.getRrs_ig())
                .place(users.getPlace())
                .photo(users.getPhoto())
                .build();
    }
}
