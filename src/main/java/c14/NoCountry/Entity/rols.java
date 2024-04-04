package c14.NoCountry.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name= "Roles")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class rols {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public  String role_name;
}
