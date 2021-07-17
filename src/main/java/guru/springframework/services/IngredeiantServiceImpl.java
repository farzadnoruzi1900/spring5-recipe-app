package guru.springframework.services;

import guru.springframework.commands.IngrediantCommand;
import guru.springframework.converters.IngradiantToIngradiantCommand;
import guru.springframework.module.Recipe;
import guru.springframework.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class IngredeiantServiceImpl implements IngredeiantService {
   private final  RecipeRepository recipeRepository;
   private  final IngradiantToIngradiantCommand ingradiantToIngradiantCommand;

    public IngredeiantServiceImpl(RecipeRepository recipeRepository, IngradiantToIngradiantCommand ingradiantToIngradiantCommand) {
        this.recipeRepository = recipeRepository;
        this.ingradiantToIngradiantCommand = ingradiantToIngradiantCommand;
    }


    @Override
    public IngrediantCommand findIngerediantbyIdWithRecipeId(Long recipeId, Long ingerediantId) {
        Optional<Recipe> optionalRecipe=recipeRepository.findById(recipeId);
        if(!optionalRecipe.isPresent()){
            log.error("the recipe you are searching for is empty"+recipeId);
        }
        Recipe recipe=optionalRecipe.get();
      Optional<IngrediantCommand>  optionalIngrediantCommand= recipe.getIngerediants().stream().filter(ingerediant -> ingerediant.getId().equals(ingerediantId))
                .map(ingerediant -> ingradiantToIngradiantCommand.convert(ingerediant))
                .findFirst();
      if(!optionalIngrediantCommand.isPresent()){
          log.error("there is not any ingerediant with this id: "+ingerediantId);
      }

        return optionalIngrediantCommand.get();
    }
}
