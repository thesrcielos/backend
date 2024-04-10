package c14.NoCountry.Repository;

import c14.NoCountry.Entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
}
