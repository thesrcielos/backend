package c14.NoCountry.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import c14.NoCountry.Entity.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByUser_Id(Integer id);
}
