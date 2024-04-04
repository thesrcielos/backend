package c14.NoCountry.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name= "Posts")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El comentario no puede estar vacio")
    private String data;

    @Lob
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private users users;

}