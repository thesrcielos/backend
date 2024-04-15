package c14.NoCountry.dto;


import c14.NoCountry.Entity.Users;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostUpdateResponse {
    @NotBlank
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String data;
    @NotBlank
    private String image;
}
