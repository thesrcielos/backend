package c14.NoCountry.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name= "Comments")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class commetns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    public posts post_id;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public users user_id;
    public String comment;
}
