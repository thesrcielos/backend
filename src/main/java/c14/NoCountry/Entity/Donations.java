package c14.NoCountry.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name= "Donations")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Donations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post id_post;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public Users users_id;
    public BigDecimal amount;
}
