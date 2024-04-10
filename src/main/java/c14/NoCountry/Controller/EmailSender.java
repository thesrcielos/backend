package c14.NoCountry.Controller;

import c14.NoCountry.Service.EmailService;
import c14.NoCountry.dto.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("email")
@RequiredArgsConstructor
public class EmailSender {
    private final EmailService emailService;
    @PostMapping("/send")
    public ResponseEntity<?> send(@RequestBody Email email){
        emailService.sendEmail(email.getTo(),email.getSubject(),email.getDescription());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
