package c14.NoCountry.dto.donations;

import c14.NoCountry.Entity.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonationSavingRequest {

    @NotNull
    private Integer id_post;
    @NotNull
    private BigDecimal amount;

}
