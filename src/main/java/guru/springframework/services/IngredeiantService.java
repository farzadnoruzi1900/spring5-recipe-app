package guru.springframework.services;

import guru.springframework.commands.IngrediantCommand;

public interface IngredeiantService {

    IngrediantCommand findIngerediantbyIdWithRecipeId(Long recipeId,Long ingerediantId);

    IngrediantCommand saveIngrediant(IngrediantCommand command);
    void deleteIngredientById(Long recipeId,Long ingredientId );

}
