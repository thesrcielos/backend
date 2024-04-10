package c14.NoCountry.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name= "Posts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre del post no debe ir vacio")
    @Column(length=25)
    private String name;

    @NotBlank(message = "El comentario no puede estar vacio")
    private String data;
    @NotBlank
    private String image;/*
    @NotBlank
    private BigDecimal recaudoEsperado;
    @NotBlank
    private BigDecimal recaudoActual;*/
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

}