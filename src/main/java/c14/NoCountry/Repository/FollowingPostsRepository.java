package c14.NoCountry.Repository;

import c14.NoCountry.Entity.FollowingPosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowingPostsRepository extends JpaRepository<FollowingPosts,Integer> {
    List<FollowingPosts> findByUserId(Integer id);
    List<FollowingPosts> findByPostId(Integer id);


}
