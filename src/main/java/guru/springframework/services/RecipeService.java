package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.module.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipe();
    Recipe findById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    RecipeCommand updateRecipeById(Long id);
    void deletById(Long id);
    RecipeCommand findCommandById(Long id);

}
