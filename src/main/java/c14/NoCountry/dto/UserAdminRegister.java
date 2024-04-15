package c14.NoCountry.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAdminRegister {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    @Size(min=8)
    private String password;
}
