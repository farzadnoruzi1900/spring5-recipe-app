package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.module.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    private final NotesToNoteCommand notesToNoteCommand;
    private final IngradiantToIngradiantCommand ingradiantToIngradiantCommand;
    private final CatagoryToCatagoryCommand catagoryToCatagoryCommand;

    public RecipeToRecipeCommand(NotesToNoteCommand notesToNoteCommand,
                                 IngradiantToIngradiantCommand ingradiantToIngradiantCommand,
                                 CatagoryToCatagoryCommand catagoryToCatagoryCommand) {
        this.notesToNoteCommand = notesToNoteCommand;
        this.ingradiantToIngradiantCommand = ingradiantToIngradiantCommand;
        this.catagoryToCatagoryCommand = catagoryToCatagoryCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source==null){
            return null;
        }
        RecipeCommand recipeCommand=new RecipeCommand();
        if(source.getIngerediants()!=null && source.getIngerediants().size()>0){
            source.getIngerediants()
                    .forEach(ingerediant -> recipeCommand.getIngredients().add(ingradiantToIngradiantCommand.convert(ingerediant)));
        }
        if(source.getCatagories()!=null && source.getCatagories().size()>0){
            source.getCatagories().
                    forEach(catagory -> recipeCommand.getCatagories().add(catagoryToCatagoryCommand.convert(catagory)));

        }
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setDificulity(source.getDifficulity());
        recipeCommand.setId(source.getId());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setNotes(notesToNoteCommand.convert(source.getNotes()));
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        return recipeCommand;
    }
}
