package c14.NoCountry.Service;

import c14.NoCountry.Entity.FollowedPosts;
import c14.NoCountry.Repository.FollowedPostsRepository;
import c14.NoCountry.dto.FollowedPostRequest;
import c14.NoCountry.dto.FollowedPostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowedPostsService {
    private final FollowedPostsRepository followingPostsRepository;
    private final FollowedPostsMapper followingPostsMapper;
    public FollowedPostResponse save(FollowedPostRequest followingPostRequest){
        FollowedPosts followingPosts= followingPostsMapper.toPost(followingPostRequest);
        return followingPostsMapper.toPostResponse(followingPostsRepository.save(followingPosts));
    }
    public void delete(Integer id){
        followingPostsRepository.deleteById(id);
    }

    public List<FollowedPostResponse> findFollowingPostsByUserId(Integer id){
        return followingPostsRepository.findByUserId(id).stream().map(followingPostsMapper::toPostResponse).toList();
    }

    public List<FollowedPostResponse> findFollowingPostsByPostId(Integer id){
        return followingPostsRepository.findByPostId(id).stream().map(followingPostsMapper::toPostResponse).toList();
    }
}
