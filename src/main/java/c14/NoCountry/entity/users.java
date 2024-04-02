package c14.NoCountry.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "Users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public  String email;
    public  String name;
    public  String lastname;
    public  String password;
    public  String rrs_fb;
    public  String rrs_ig;
    public  String place;
    public  String photo;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    public  roles role_id;
}
