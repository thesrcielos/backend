package c14.NoCountry.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "Posts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String data;
    @Lob
    public byte[] image;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public users id_user;
}
