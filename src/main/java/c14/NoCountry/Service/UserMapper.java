package c14.NoCountry.Service;

import c14.NoCountry.Entity.Role;
import c14.NoCountry.Entity.Users;
import c14.NoCountry.dto.user.*;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserResponse toUserResponse(Users users){
    if(users==null){
        throw new NullPointerException("User cant be null");
    }
        return UserResponse.builder()
                .id(users.getId())
                .email(users.getEmail())
                .name(users.getName())
                .lastname(users.getLastname())
                .rrs_fb(users.getRrs_fb())
                .rrs_ig(users.getRrs_ig())
                .place(users.getPlace())
                .photo(users.getPhoto())
                .role(users.getRole())
                .build();
    }
    public Users userUpdateToUser(UserUpdateResponse user){
        if(user==null){
            throw new NullPointerException("User cant be null");
        }
        return Users.builder()
                .name(user.getName())
                .lastname(user.getLastname())
                .rrs_fb(user.getRrs_fb())
                .rrs_ig(user.getRrs_ig())
                .place(user.getPlace())
                .photo(user.getPhoto())
                .build();
    }
    public UserUpdateResponse toUserUpdateResponse(Users users){
        if(users==null){
            throw new NullPointerException("User cant be null");
        }
        return UserUpdateResponse.builder()
                .name(users.getName())
                .lastname(users.getLastname())
                .rrs_fb(users.getRrs_fb())
                .rrs_ig(users.getRrs_ig())
                .place(users.getPlace())
                .photo(users.getPhoto())
                .build();
    }

    public Users userDonorToUser(UserDonorRegister users){
        if(users==null){
            throw new NullPointerException("User cant be null");
        }
        return Users.builder()
                .email(users.getEmail())
                .name(users.getName())
                .lastname(users.getLastname())
                .password(users.getPassword())
                .role(Role.DONOR)
                .build();
    }

    public Users userCreatorToUser(UserCreatorRegister users){
        if(users==null){
            throw new NullPointerException("User cant be null");
        }
        return Users.builder()
                .email(users.getEmail())
                .name(users.getName())
                .lastname(users.getLastname())
                .password(users.getPassword())
                .rrs_fb(users.getRrs_fb())
                .rrs_ig(users.getRrs_ig())
                .place(users.getPlace())
                .photo(users.getPhoto())
                .role(Role.CREATOR)
                .build();
    }

    public Users userAdminToUser(UserAdminRegister users){
        if(users==null){
            throw new NullPointerException("User cant be null");
        }
        return Users.builder()
                .name(users.getName())
                .email(users.getEmail())
                .password(users.getPassword())
                .role(Role.ADMIN).build();
    }
}
