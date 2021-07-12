package guru.springframework.module;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = {"recipe"})
public class Catagory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToMany(mappedBy="catagories")
    private Set<Recipe> recipe=new HashSet<>();

    public Catagory() {
    }

}
