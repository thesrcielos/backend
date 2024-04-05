package c14.NoCountry.Service;

import c14.NoCountry.Repository.PostRepository;
import c14.NoCountry.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import c14.NoCountry.Entity.Post;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    public Post save(Post post){
        return postRepository.save(post);
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
}
