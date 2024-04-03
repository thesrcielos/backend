package c14.NoCountry.Controller;

import c14.NoCountry.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import c14.NoCountry.Entity.Post;

@RestController
public class PostController {

    @Autowired
    private PostService pse;

    @PostMapping("/save-post")
    public ResponseEntity<?> savePost(Post pss){
        if (pss==null){
            return ResponseEntity.badRequest().body("No puede haber datos vacios");
        }
       return ResponseEntity.ok(pse.save(pss));
    }

    @GetMapping("/all-post")
    public ResponseEntity<?> getPost() {
        return ResponseEntity.ok(pse.findByAll());
    }
}
