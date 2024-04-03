package c14.NoCountry.Service;

import c14.NoCountry.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import c14.NoCountry.Entity.Post;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository ps;

    public Post save(Post pst){
        return ps.save(pst);
    }

    public List<Post> findByAll(){
       return ps.findAll();

    }
}
