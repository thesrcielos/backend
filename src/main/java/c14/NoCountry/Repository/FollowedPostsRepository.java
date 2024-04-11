package c14.NoCountry.Repository;

import c14.NoCountry.Entity.FollowedPosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowedPostsRepository extends JpaRepository<FollowedPosts,Integer> {
    List<FollowedPosts> findByUserId(Integer id);
    List<FollowedPosts> findByPostId(Integer id);


}
