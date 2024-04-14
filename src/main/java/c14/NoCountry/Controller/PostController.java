package c14.NoCountry.Controller;

import c14.NoCountry.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import c14.NoCountry.Entity.Post;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@CrossOrigin
public class PostController {

    private final PostService postService;

//// la anotacion @valid se usa para activar la validacion de entrada para post
//    el parametro BindingResult lo usamos para manejar las excepciones de validacion

    @PreAuthorize("hasRole('CREATOR')")
    @PostMapping("/save-post")
    public ResponseEntity<?> savePost(@Valid @RequestBody Post post, BindingResult result){

        if (result.hasErrors()) {
            List<String> errorMessages = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
// si hay errores de validacion, se devuelven en una lista en una utilizando lo siguiente:
            return ResponseEntity.badRequest().body(errorMessages);
        }
       return ResponseEntity.ok(postService.save(post));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all-post")
    public ResponseEntity<?> getPosts() {
        return ResponseEntity.ok(postService.findByAll());
    }

    @PreAuthorize("hasRole('CREATOR')")
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getPostsByUserId(@PathVariable Integer id){
        return ResponseEntity.ok(postService.findByUserId(id));
    }

    @PreAuthorize("hasRole('CREATOR')")
    @PutMapping("/updatePost")
    public ResponseEntity<?> updatePost(@Valid @RequestBody Post post){
        return ResponseEntity.ok(postService.save(post));
    }

    @PreAuthorize("hasAnyRole('CREATOR','ADMIN')")
    @DeleteMapping("deletePost/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Integer id){
        postService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/searchByName")
    public ResponseEntity<?> searchByName(@RequestParam("searchTerm") String searchTerm) {
        List<Post> searchResult = postService.searchProjectByName(searchTerm);
        return ResponseEntity.ok(searchResult);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/searchByData")
    public ResponseEntity<?> searchByData(@RequestParam("searchTerm") String searchTerm) {
        List<Post> searchResult = postService.searchProjectByData(searchTerm);
        return ResponseEntity.ok(searchResult);
    }
}

