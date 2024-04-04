package c14.NoCountry.Controller;

import c14.NoCountry.Entity.comments;
import c14.NoCountry.Entity.donations;
import c14.NoCountry.Service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentsController {
    @Autowired
    private CommentsService cmts;

    @PostMapping("/save-comment")
    public ResponseEntity<?> saveComment(comments cmts){
        if (cmts==null){
            return ResponseEntity.badRequest().body("No puede haber datos vacios");
        }
        return ResponseEntity.ok(this.cmts.save(cmts));
    }

    @GetMapping("/all-comment")
    public ResponseEntity<?> getComments() {
        return ResponseEntity.ok(cmts.findByAll());
    }
}
