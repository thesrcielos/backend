package c14.NoCountry.Service;

import c14.NoCountry.Entity.FollowingPosts;
import c14.NoCountry.Entity.Post;
import c14.NoCountry.dto.FollowingPostRequest;
import c14.NoCountry.dto.FollowingPostResponse;
import org.springframework.stereotype.Service;

@Service
public class FollowingPostsMapper {
    public FollowingPosts toPost(FollowingPostRequest followingPostRequest){
        if(followingPostRequest==null){
            throw new NullPointerException("FollowingPostRequest cant be null");
        }
        return FollowingPosts.builder()
                .user(followingPostRequest.getUser())
                .post(followingPostRequest.getPost())
                .build();
    }
    public FollowingPostResponse toPostResponse(FollowingPosts followingPosts){
        if(followingPosts==null){
            throw new NullPointerException("FollowingPost cant be null");
        }
        return FollowingPostResponse.builder()
                .id(followingPosts.getId())
                .user(followingPosts.getUser())
                .post(followingPosts.getPost())
                .build();
    }
}
