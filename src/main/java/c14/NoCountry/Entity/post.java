package c14.NoCountry.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

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

    @NotBlank(message = "El nombre del post no debe ir vacio")
    @Column(length=25)
    private String name;

    @NotBlank(message = "El comentario no puede estar vacio")
    private String data;

    @Lob
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private users users;

}