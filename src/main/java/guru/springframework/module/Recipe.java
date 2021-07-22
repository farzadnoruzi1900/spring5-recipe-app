package guru.springframework.module;


import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // the generation type identity to have autogenerated value in our data-base
    // of course the data base should support this feature .

    private String description;

    private Integer prepTime;

    private Integer cookTime;

    private Integer servings;
    private String source;

    private String url ;
    @Lob

    private String directions;
    @Enumerated(value = EnumType.STRING)
    private Difficulity difficulity;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "recipe")
    private Set<Ingerediant> ingerediants =new HashSet<>();

    @Lob
    private Byte[] image;
    // when we want jpa support large Byte object without any limitation
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;
    // we have one way cascading from Recipe to note
    @ManyToMany
    @JoinTable(name = "recipe_catagory",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "catagory_id"))
    private Set<Catagory> catagories=new HashSet<>();

    public Recipe() {
    }
    public void setNotes(Notes notes) {
        // this is where you have to add another assignment in bidirectional relationship
        this.notes = notes;
        notes.setRecipe(this);
    }
    // add this method to have more clean code in bootstrap and to omit the redundancy of adding the
    // Recipe object to ingrediant because it is bidirectional relation.
    public void addIngrediant(Ingerediant ingerediant){
        this.ingerediants.add(ingerediant);
        ingerediant.setRecipe(this);
    }
}
