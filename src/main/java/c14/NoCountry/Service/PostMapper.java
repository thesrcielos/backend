package c14.NoCountry.Service;

import c14.NoCountry.Entity.Post;
import c14.NoCountry.dto.PostResponse;
import c14.NoCountry.dto.PostUpdateResponse;
import org.springframework.stereotype.Service;

@Service
public class PostMapper {
    public PostResponse toPostResponse(Post post){
    if(post==null){
        throw new NullPointerException("Post cant be null");
    }
        return PostResponse.builder()
                //.id(post.getId())
                .name(post.getName())
                .data(post.getData())
                .image(post.getImage())
                .user(post.getUser())
                .build();
    }

    public PostUpdateResponse toPostUpdateResponse(Post post){
        if(post==null){
            throw new NullPointerException("Post cant be null");
        }
        return PostUpdateResponse.builder()
                .id(post.getId())
                .name(post.getName())
                .data(post.getData())
                .image(post.getImage())
                .build();
    }

}
