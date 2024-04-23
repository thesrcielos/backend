package c14.NoCountry.Service;

import c14.NoCountry.Entity.Users;
import c14.NoCountry.Repository.PostRepository;
import c14.NoCountry.dto.post.PostResponse;
import c14.NoCountry.dto.post.PostSavingRequest;
import c14.NoCountry.dto.post.PostUpdateRequest;
import c14.NoCountry.exception.RequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import c14.NoCountry.Entity.Post;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserService userService;
    public PostResponse save(PostSavingRequest postRequest){
        Post post = postMapper.postRequestToPost(postRequest);
        Users user = userService.getUserFromSecurityContextHolder();
        post.setUser(user);
        return postMapper.toPostResponse(postRepository.save(post));
    }

    public void delete(Integer id){
        postRepository.deleteById(id);
    }
    public List<PostResponse> findByAll(){
        return postRepository.findAll().stream()
                .map(postMapper::toPostResponse).toList();
    }
    public List<PostResponse> findByUserId(Integer id){
        return postRepository.findByUser_Id(id).stream().map(postMapper::toPostResponse).toList();
    }
    public PostResponse findByPostId(Integer id){
        Post post = postRepository.findById(id).orElseThrow(()->new RequestException("P-404","Post not found"));
        return postMapper.toPostResponse(post);
    }
    public List<Post> searchProjectByName(String searchTerm) {
        return postRepository.searchProjectByName(searchTerm);
    }

    public List<Post> searchProjectByData(String searchTerm) {
        return postRepository.searchProjectByData(searchTerm);
    }

    public PostResponse update(PostUpdateRequest postRequest) throws RequestException {
        Post post = postRepository.findById(postRequest.getId()).orElseThrow(()->new RequestException("401","Post with that id doesnt exist"));
        post.setName(postRequest.getName());
        post.setData(postRequest.getData());
        post.setImage(postRequest.getImage());
        return postMapper.toPostResponse(postRepository.save(post));
    }
    //Metodos Genericos para el manejo de excepciones:
    //Metodo para campos nulos o vacios
    public void validateField(String field, String fieldName, String errorCode) {
        if (field == null || field.isEmpty()) {
            throw new RequestException(errorCode, fieldName + " is required");
        }
    }

    public Post getPostById(Integer postId) {
        System.out.println("el id en Post es : " + postId);
        // Verifica si el postId es válido
        if (postId == null || postId == 0) {
            throw new IllegalArgumentException("El Id es invalido o inexistente.");
        }
        // Intenta obtener el post por su ID desde el repositorio
        Optional<Post> postOptional = postRepository.findById(postId);

        // Si el post existe, devuélvelo; de lo contrario, lanza una excepción
        return postOptional.orElseThrow(() -> new RuntimeException("No se encontró ningún post con el ID: " + postId));
    }
}


