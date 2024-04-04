package c14.NoCountry.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "Users")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Data
public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
    private rols role;

}