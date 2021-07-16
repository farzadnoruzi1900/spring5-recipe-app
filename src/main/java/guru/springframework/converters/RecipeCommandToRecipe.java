package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.module.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
    private final NoteCommandToNotes noteCommandToNotes;
    private final IngradiantCommandToIngradiant ingradiantCommandToIngradiant;
    private final CatagoryCommandToCatagory catagoryCommandToCatagory;

    public RecipeCommandToRecipe(NoteCommandToNotes notesCommand, IngradiantCommandToIngradiant ingrediantCommand, CatagoryCommandToCatagory catagoryCommand) {
        this.noteCommandToNotes = notesCommand;
        this.ingradiantCommandToIngradiant = ingrediantCommand;
        this.catagoryCommandToCatagory = catagoryCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }
        Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setNotes(Objects.requireNonNull(noteCommandToNotes.convert(source.getNotes())));
        recipe.setDifficulity(source.getDificulity());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setDescription(source.getDescription());
        recipe.setCookTime(source.getCookTime());
        if (source.getCatagories() != null && source.getCatagories().size() > 0) {
    source.getCatagories().
            forEach(catagoryCommand -> recipe.getCatagories().add(catagoryCommandToCatagory.convert(catagoryCommand)));
        }
        if(source.getIngredients()!=null && source.getIngredients().size()>0){
            source.getIngredients()
                    .forEach(ingrediantCommand -> recipe.getIngerediants().add(ingradiantCommandToIngradiant.convert(ingrediantCommand)));
        }
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        return recipe;
    }
}
