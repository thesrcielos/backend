package c14.NoCountry.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import c14.NoCountry.Entity.Post;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByUser_Id(Integer id);

    @Query(value = "SELECT * FROM posts WHERE name LIKE %?1%", nativeQuery = true)
    List<Post> searchProjectByName(String searchTerm);
    @Query(value = "SELECT * FROM posts WHERE data LIKE %?1%", nativeQuery = true)
    List<Post> searchProjectByData(String searchTerm);
}