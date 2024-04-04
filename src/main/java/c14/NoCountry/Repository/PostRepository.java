package c14.NoCountry.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import c14.NoCountry.Entity.post;

import java.util.List;

public interface PostRepository extends JpaRepository<post, Long> {

    List<post> findByName(String name);
}
