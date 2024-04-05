package c14.NoCountry.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Table(name= "Users")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Email(message = "El correo electronico no es valido")
    private String email;
    private String name;
    private String lastname;
    private String password;
    private String rrs_fb;
    private String rrs_ig;
    private String place;
    private String photo;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Rols role;

}