package c14.NoCountry.dto;

import c14.NoCountry.Entity.Post;
import c14.NoCountry.Entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowedPostRequest {
    private Users user;
    private Post post;
}
