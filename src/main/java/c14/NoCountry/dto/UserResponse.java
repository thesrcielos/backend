package c14.NoCountry.dto;


import c14.NoCountry.Entity.Rols;
import c14.NoCountry.Entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String email;
    private String name;
    private String lastname;
    private String password;
    private String rrs_fb;
    private String rrs_ig;
    private String place;
    private String photo;
    private Rols role;
}
