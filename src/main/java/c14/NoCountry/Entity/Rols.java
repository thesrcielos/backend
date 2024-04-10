package c14.NoCountry.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Entity
@Table(name= "Roles")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rols {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public  String role_name;

    public class Constants {
        public static final List<String> PREDEFINED_ROLES = Arrays.asList("ADMIN", "USER", "SPONSOR");
    }
}
