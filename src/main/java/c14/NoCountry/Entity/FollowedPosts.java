package c14.NoCountry.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "FollowedPosts", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id,post_id"}))
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FollowedPosts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
