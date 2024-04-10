package c14.NoCountry.Service;

import c14.NoCountry.Entity.FollowingPosts;
import c14.NoCountry.Repository.FollowingPostsRepository;
import c14.NoCountry.dto.FollowingPostRequest;
import c14.NoCountry.dto.FollowingPostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowingPostsService {
    private final FollowingPostsRepository followingPostsRepository;
    private final FollowingPostsMapper followingPostsMapper;
    public FollowingPostResponse save(FollowingPostRequest followingPostRequest){
        FollowingPosts followingPosts= followingPostsMapper.toPost(followingPostRequest);
        return followingPostsMapper.toPostResponse(followingPostsRepository.save(followingPosts));
    }
    public void delete(Integer id){
        followingPostsRepository.deleteById(id);
    }
}
