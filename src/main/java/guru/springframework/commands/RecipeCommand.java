package guru.springframework.commands;

import guru.springframework.module.Difficulity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    @Min(1)
    @Max(900)
    private Integer prepTime;
    @NotBlank
    @Size(min=3 ,max=255)
 /*   we assign 255 as default because the hibernate when create an string file into database
            it creates a 255 capacity chart . */
    private String description;
    @Min(1)
    @Max(900)
    private Integer cookTime;
    @Min(1)
    @Max(100)
    private Integer servings;
    private String source;
    @URL
    private String url;
    @NotBlank
    private  String directions;
    private Set<IngrediantCommand> ingredients=new HashSet<>();
    private Set<CatagoryCommand> catagories=new HashSet<>();
    private Difficulity dificulity;
    private NotesCommand notes;
    private Byte[] image;

}
