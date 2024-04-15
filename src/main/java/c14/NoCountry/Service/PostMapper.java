package c14.NoCountry.Service;

import c14.NoCountry.Entity.Post;
import c14.NoCountry.dto.PostResponse;
import c14.NoCountry.dto.PostSavingRequest;
import c14.NoCountry.dto.PostUpdateRequest;
import org.springframework.stereotype.Service;

@Service
public class PostMapper {
    public PostResponse toPostResponse(Post post){
    if(post==null){
        throw new NullPointerException("Post cant be null");
    }
        return PostResponse.builder()
                .id(post.getId())
                .name(post.getName())
                .data(post.getData())
                .image(post.getImage())
                .user_id(post.getUser().getId())
                .build();
    }

    public Post postUpdateToPost(PostUpdateRequest post){
        if(post==null){
            throw new NullPointerException("Post cant be null");
        }
        return Post.builder()
                .name(post.getName())
                .data(post.getData())
                .image(post.getImage())
                .build();
    }

    public Post postRequestToPost(PostSavingRequest post){
        if(post==null){
            throw new NullPointerException("Post cant be null");
        }
        return Post.builder()
                .name(post.getName())
                .data(post.getData())
                .image(post.getImage())
                .build();
    }

}
