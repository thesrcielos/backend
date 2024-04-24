package c14.NoCountry.Controller;

import c14.NoCountry.Service.DonationsService;
import c14.NoCountry.dto.donations.DonationResponse;
import c14.NoCountry.dto.donations.DonationSavingRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/donation")
@RequiredArgsConstructor
@CrossOrigin
public class DonationsController {
    @Autowired
    private final DonationsService donationsService;
    @PostMapping("/save-donation")
    public ResponseEntity<?> saveDonation(@Valid @RequestBody DonationSavingRequest donations,
                                          BindingResult result){
        //System.out.println("El id en el controller es : " + id);
        if(result.hasErrors()){
            List<String> errorMessages = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errorMessages);
        }
        return ResponseEntity.ok(donationsService.save(donations,donations.getId_post()));
    }
    @GetMapping("/all-donations")
    public ResponseEntity<?> getDonation() {
        return ResponseEntity.ok(donationsService.findByAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getDonationsByUserId(@PathVariable Integer id){
        return ResponseEntity.ok(donationsService.findByUserId(id));
    }
}
