package c14.NoCountry.dto.post;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostSavingRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String data;
    private String image;

}
