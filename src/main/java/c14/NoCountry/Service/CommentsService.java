package c14.NoCountry.Service;

import c14.NoCountry.Entity.Comments;
import c14.NoCountry.Repository.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService {
    @Autowired
    CommentsRepository cr;

    public Comments save(Comments cmt){
        return cr.save(cmt);
    }

    public List<Comments> findByAll(){
        return cr.findAll();

    }
}
