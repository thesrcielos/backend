package c14.NoCountry.Controller;


import c14.NoCountry.Service.FollowedPostsService;
import c14.NoCountry.dto.FollowedPostRequest;
import c14.NoCountry.dto.FollowedPostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/followingposts")
@RequiredArgsConstructor
public class FollowedPostsController {
    private final FollowedPostsService followedPostsService;
    @PostMapping("/follow-post")
    public ResponseEntity<?> followPost(FollowedPostRequest request){
        FollowedPostResponse response = followedPostsService.save(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/unfollow-post")
    public ResponseEntity<?> unfollowPost(Integer id){
        followedPostsService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/followed-posts")
    public ResponseEntity<?> followedPosts(Integer id){
        return ResponseEntity.ok(followedPostsService.findFollowingPostsByUserId(id));

    }
}
