package c14.NoCountry.Controller;

import c14.NoCountry.Entity.Comments;
import c14.NoCountry.Service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentsController {
    @Autowired
    private CommentsService commentsService;

    @PostMapping("/save-comment")
    public ResponseEntity<?> saveComment(Comments cmts){
        return ResponseEntity.ok(this.commentsService.save(cmts));
    }

    @GetMapping("/all-comment")
    public ResponseEntity<?> getComments() {
        return ResponseEntity.ok(commentsService.findByAll());
    }
}
