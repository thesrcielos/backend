package c14.NoCountry.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import c14.NoCountry.Entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
