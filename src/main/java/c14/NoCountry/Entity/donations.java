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
public class donations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private c14.NoCountry.Entity.post post;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public users users_id;
    public BigDecimal amount;
}
