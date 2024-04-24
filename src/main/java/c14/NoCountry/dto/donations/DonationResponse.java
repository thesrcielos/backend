package c14.NoCountry.dto.donations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonationResponse {
    private Integer id;
    private Integer post_id;
    private Integer user_id;
    private BigDecimal amount;
}
