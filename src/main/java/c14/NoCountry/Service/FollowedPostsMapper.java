package c14.NoCountry.Service;

import c14.NoCountry.Entity.FollowedPosts;
import c14.NoCountry.dto.FollowedPostRequest;
import c14.NoCountry.dto.FollowedPostResponse;
import org.springframework.stereotype.Service;

@Service
public class FollowedPostsMapper {
    public FollowedPosts toPost(FollowedPostRequest followingPostRequest){
        if(followingPostRequest==null){
            throw new NullPointerException("FollowingPostRequest cant be null");
        }
        return FollowedPosts.builder()
                .user(followingPostRequest.getUser())
                .post(followingPostRequest.getPost())
                .build();
    }
    public FollowedPostResponse toPostResponse(FollowedPosts followingPosts){
        if(followingPosts==null){
            throw new NullPointerException("FollowingPost cant be null");
        }
        return FollowedPostResponse.builder()
                .id(followingPosts.getId())
                .user(followingPosts.getUser())
                .post(followingPosts.getPost())
                .build();
    }
}
