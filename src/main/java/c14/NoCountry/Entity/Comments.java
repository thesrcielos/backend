package c14.NoCountry.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "Comments")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comments {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        public int id;
        @ManyToOne
        @JoinColumn(name = "post_id", referencedColumnName = "id")
        public Post post_id;
        @OneToOne
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        public Users user_id;
        public String comment;
    }
