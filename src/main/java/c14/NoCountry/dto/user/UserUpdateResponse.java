package c14.NoCountry.dto.user;


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
    @NotBlank
    private String name;
    @NotBlank
    private String lastname;
    private String rrs_fb;
    private String rrs_ig;
    private String place;
    private String photo;
}
