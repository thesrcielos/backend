package c14.NoCountry.Controller;

import c14.NoCountry.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import c14.NoCountry.Entity.post;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService pse;

    @PostMapping("/save-post")
    public ResponseEntity<?> savePost(post pss){
        if (pss==null){
            return ResponseEntity.badRequest().body("No puede haber datos vacios");
        }
       return ResponseEntity.ok(pse.save(pss));
    }

    @GetMapping("/all-post")
    public ResponseEntity<?> getPost() {
        return ResponseEntity.ok(pse.findByAll());
    }

    @GetMapping("/getpost/{name}")
    public ResponseEntity<?> getName(@PathVariable String name) {
        List<post> posts = pse.findbyName(name);
        if(posts.isEmpty()) {
            return ResponseEntity.badRequest().body("No existe el nombre del póst");
        }
        return ResponseEntity.ok(posts);
    }

}

