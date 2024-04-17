package c14.NoCountry.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostUpdateRequest {
    @NotBlank
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String data;
    @NotBlank
    private String image;
    @NotBlank
    private Integer user_id;
}
