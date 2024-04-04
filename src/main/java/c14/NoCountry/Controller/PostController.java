package c14.NoCountry.Controller;

import c14.NoCountry.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import c14.NoCountry.Entity.post;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PostController {

    @Autowired
    private PostService pse;

//// la anotacion @valid se usa para activar la validacion de entrada para post
//    el parametro BindingResult lo usamos para manejar las excepciones de validacion

    @PostMapping("/save-post")
    public ResponseEntity<?> savePost(@Valid @RequestBody post pss, BindingResult result){

        if (result.hasErrors()) {
            List<String> errorMessages = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
// si hay errores de validacion, se devuelven en una lista en una utilizando lo siguiente:
            return ResponseEntity.badRequest().body(errorMessages);
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

