package c14.NoCountry.Service;

import c14.NoCountry.Entity.Role;
import c14.NoCountry.Entity.Users;
import c14.NoCountry.Repository.PostRepository;
import c14.NoCountry.dto.PostResponse;
import c14.NoCountry.dto.PostSavingRequest;
import c14.NoCountry.dto.PostUpdateRequest;
import c14.NoCountry.dto.UserResponse;
import c14.NoCountry.exception.RequestException;
import c14.NoCountry.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import c14.NoCountry.Entity.Post;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public List<Post> searchProjectByName(String searchTerm) {
        return postRepository.searchProjectByData(searchTerm);
    }

    public List<Post> searchProjectByData(String searchTerm) {
        return postRepository.searchProjectByData(searchTerm);
    }

    public PostResponse update(PostUpdateRequest postRequest) throws Exception {
        Optional<Post> postSaved = postRepository.findById(postRequest.getId());
        if(postSaved.isEmpty()){
            throw new Exception("Post not found");
        }
        Post post = postMapper.postUpdateToPost(postRequest);
        return postMapper.toPostResponse(postRepository.save(post));
    }
    //Metodos Genericos para el manejo de excepciones:
    //Metodo para campos nulos o vacios
    public void validateField(String field, String fieldName, String errorCode) {
        if (field == null || field.isEmpty()) {
            throw new RequestException(errorCode, fieldName + " is required");
        }
    }
    //Metodo para la verificacion de acceso a los end points


}
