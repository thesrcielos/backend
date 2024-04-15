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
    private Integer id;
    private String name;
    private String data;
    private String image;
    private Integer user_id;
}
