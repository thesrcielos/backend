package c14.NoCountry.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table(name= "Roles")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   public int id;
   public  String role_name;
}
