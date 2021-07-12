package guru.springframework.module;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(exclude = {"recipe"})
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Recipe recipe;
    @Lob
    private String direction;

    public Notes() {
    }

}
