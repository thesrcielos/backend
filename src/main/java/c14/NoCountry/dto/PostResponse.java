package c14.NoCountry.dto;


import c14.NoCountry.Entity.Users;
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
public class PostResponse {
    @NotBlank
    private String name;
    @NotBlank
    private String data;
    @NotBlank
    private String image;
    @NotNull
    private Users user;
}
