package c14.NoCountry.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    @NotBlank
    private String to;
    @NotBlank
    private String subject;
    @NotBlank
    private String description;
}
