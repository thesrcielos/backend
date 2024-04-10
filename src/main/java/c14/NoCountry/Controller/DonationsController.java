package c14.NoCountry.Controller;

import c14.NoCountry.Entity.Donations;
import c14.NoCountry.Service.DonationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DonationsController {
    @Autowired
    private DonationsService dnts;

    @PostMapping("/save-donacion")
    public ResponseEntity<?> saveDonation(Donations dnts){

        return ResponseEntity.ok(this.dnts.save(dnts));
    }
    @GetMapping("/all-donations")
    public ResponseEntity<?> getDonation() {
        return ResponseEntity.ok(dnts.findByAll());
    }

}
