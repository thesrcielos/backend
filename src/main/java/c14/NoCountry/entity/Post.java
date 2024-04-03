package c14.NoCountry.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "Posts")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String data;

    @Lob
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private users users;


}