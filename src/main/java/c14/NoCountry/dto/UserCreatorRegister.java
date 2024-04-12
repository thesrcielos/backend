package c14.NoCountry.dto;

import c14.NoCountry.Entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatorRegister {
    @Email(message = "El correo electronico no es valido")
    private String email;
    private String name;
    @Size(min = 8)
    private String password;
    private String rrs_fb;
    private String rrs_ig;
    private String place;
    private String photo;
}
