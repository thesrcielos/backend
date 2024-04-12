package c14.NoCountry.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAdminRegister {
    private String name;
    private String email;
    @Size(min=8)
    private String password;
}
