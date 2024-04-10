package c14.NoCountry.Controller;

import c14.NoCountry.Entity.Rols;
import c14.NoCountry.Service.RolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RolsController {
    @Autowired
    private RolsService rlss;

    @PostMapping("/save-rol")
    public ResponseEntity<?> saveComment(Rols rls){
        if (rls==null){
            return ResponseEntity.badRequest().body("No puede haber datos vacios");
        }
        return ResponseEntity.ok(this.rlss.save(rls));
    }

    @GetMapping("/all-roles")
    public ResponseEntity<?> getComments() {
        return ResponseEntity.ok(rlss.findByAll());
    }
}
