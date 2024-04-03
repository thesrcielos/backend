package c14.NoCountry.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import c14.NoCountry.Entity.Post;

@Service
public class PostService {

    @Autowired
    PostService ps;

    public Post save(Post pst){
        return ps.save(pst);
    }
}
