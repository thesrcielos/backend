package c14.NoCountry.dto;


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
public class PostUpdateRequest {
    @NotNull
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String data;
    @NotBlank
    private String image;
}
