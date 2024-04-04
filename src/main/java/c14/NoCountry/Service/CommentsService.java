package c14.NoCountry.Service;

import c14.NoCountry.Entity.comments;
import c14.NoCountry.Entity.donations;
import c14.NoCountry.Repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {
    @Autowired
    CommentsRepository cr;

    public comments save(comments cmt){
        return cr.save(cmt);
    }

    public List<comments> findByAll(){
        return cr.findAll();

    }
}
