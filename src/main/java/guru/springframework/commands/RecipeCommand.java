package guru.springframework.commands;

import guru.springframework.module.Difficulity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private Integer prepTime;
    private String description;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private  String directions;
    private Set<IngrediantCommand> ingredients=new HashSet<>();
    private Set<CatagoryCommand> catagories=new HashSet<>();
    private Difficulity dificulity;
    private NotesCommand notes;

}
