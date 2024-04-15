package c14.NoCountry.dto;

import c14.NoCountry.Entity.Post;
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
public class FollowedPostRequest {
    @NotBlank
    private Users user;
    @NotBlank
    private Post post;
}
