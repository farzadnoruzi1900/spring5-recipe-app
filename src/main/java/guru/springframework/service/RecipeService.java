package guru.springframework.service;

import guru.springframework.module.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipe();
}
