package c14.NoCountry.Controller;

import c14.NoCountry.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import c14.NoCountry.Entity.Post;

@RestController
public class PostController {

    @Autowired
    private PostService pse;

    @PostMapping("/save-post")
    public ResponseEntity<?> savePost(Post pss){
       return ResponseEntity.ok(pse.save(pss));
    }
}
