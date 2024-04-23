package c14.NoCountry.dto.user;

import c14.NoCountry.Entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDonorRegister {
    @NotBlank
    @Email(message = "El correo electronico no es valido")
    private String email;
    @NotBlank
    private String name;
    private String lastname;
    @NotBlank
    @Size(min = 8)
    private String password;

}
