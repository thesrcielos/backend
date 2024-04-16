package c14.NoCountry.Service;

import c14.NoCountry.Entity.Users;
import c14.NoCountry.Repository.PostRepository;
import c14.NoCountry.dto.PostResponse;
import c14.NoCountry.dto.PostSavingRequest;
import c14.NoCountry.dto.PostUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import c14.NoCountry.Entity.Post;

import java.util.List;

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
        Users user = userService.getUserById(postRequest.getUser_id()).orElseThrow(()->new Exception("usuario no existe"));
        Post post = postMapper.postUpdateToPost(postRequest);
        return postMapper.toPostResponse(postRepository.save(post));
    }
}
