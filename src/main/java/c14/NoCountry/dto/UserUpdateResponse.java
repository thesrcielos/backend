package c14.NoCountry.dto;


import c14.NoCountry.Entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateResponse {
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String lastname;
    @NotBlank
    private String password;
    @NotBlank
    private String rrs_fb;
    @NotBlank
    private String rrs_ig;
    @NotBlank
    private String place;
    @NotBlank
    private String photo;
    @NotNull
    private Role role;
}
