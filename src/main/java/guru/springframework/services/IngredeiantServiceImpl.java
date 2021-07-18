package guru.springframework.services;

import guru.springframework.commands.IngrediantCommand;
import guru.springframework.converters.IngradiantCommandToIngradiant;
import guru.springframework.converters.IngradiantToIngradiantCommand;
import guru.springframework.module.Ingerediant;
import guru.springframework.module.Recipe;
import guru.springframework.repository.RecipeRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
public class IngredeiantServiceImpl implements IngredeiantService {
    private final RecipeRepository recipeRepository;
    private final IngradiantToIngradiantCommand ingradiantToIngradiantCommand;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngradiantCommandToIngradiant ingradiantCommandToIngradiant;

    public IngredeiantServiceImpl(RecipeRepository recipeRepository, IngradiantToIngradiantCommand ingradiantToIngradiantCommand, UnitOfMeasureRepository unitOfMeasureRepository, IngradiantCommandToIngradiant ingradiantCommandToIngradiant) {
        this.recipeRepository = recipeRepository;
        this.ingradiantToIngradiantCommand = ingradiantToIngradiantCommand;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingradiantCommandToIngradiant = ingradiantCommandToIngradiant;
    }


    @Override
    public IngrediantCommand findIngerediantbyIdWithRecipeId(Long recipeId, Long ingerediantId) {

Optional<Recipe> optionalRecipe=recipeRepository.findById(recipeId);
if(!optionalRecipe.isPresent()){
    log.error("the recipe with this id does not exist : "+recipeId);
}
Recipe recipe=optionalRecipe.get();
Optional<Ingerediant> optionalIngerediant=recipe.getIngerediants()
        .stream().filter(ingerediant -> ingerediant.getId().equals(ingerediantId))
        .findFirst();
if(!optionalIngerediant.isPresent()){
    log.error("there is not any ingrediant with this id : " + ingerediantId);
}

        return ingradiantToIngradiantCommand.convert(optionalIngerediant.get());

    }


    /* but in Ingrediant as you know there is upside dependency when you wnat to
            save an Ingerediant you have to control the recipe to be sure
            that a recipe for this ingrediant is exist and if exist you have to get
    its list of ingrediant and if there is an ingrediant in there with exact number of id
    with the ingrediant that we try to save go and update that ingrediant with the new value
    of this new ingrediant that we want to save . again for adding unitofmeasure
            we ahev to be sure that the unitofmeasure of the new ingerediant exists in the
            repository if not throw exception .
            in case the ingrediant you want to add is not part of recipe ingrediant just add
            it to recipe ingrediants .
    then save the recipe in the repo*/
    @Transactional
    @Override
    public IngrediantCommand saveIngrediant(IngrediantCommand command) {
        Optional<Recipe> recipeOpt = recipeRepository.findById(command.getRecipeId());
        if (!recipeOpt.isPresent()) {
            log.error("there is not any recipe related to this ingerediant with this id " + command.getId());
            return new IngrediantCommand();
        }
        Recipe recipe = recipeOpt.get();
        Optional<Ingerediant> optionalIngerediant = recipe.getIngerediants().stream()
                .filter(ingerediant -> ingerediant.getId().equals(command.getId()))
                .findFirst();
        if (optionalIngerediant.isPresent()) {
            Ingerediant ingerediant = optionalIngerediant.get();
            ingerediant.setId(command.getId());
            ingerediant.setDescription(command.getDescription());
            ingerediant.setAmount(command.getAmount());
            ingerediant.setUnitOfMeasure(
                    unitOfMeasureRepository.findById(command.getUnitOfMeasureCommand().getId())
                            .orElseThrow(() -> new RuntimeException("the uom does not exists")));
        } else {
            recipe.addIngrediant(ingradiantCommandToIngradiant.convert(command));
        }
        Recipe saveRecipe = recipeRepository.save(recipe);

        return ingradiantToIngradiantCommand.convert(saveRecipe.getIngerediants()
                .stream().filter(ingerediant -> ingerediant.getId().equals(command.getId()))
                .findFirst().get());
    }

    @Override
    public void deleteIngredientById(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional=recipeRepository.findById(recipeId);
        if(recipeOptional.isPresent()){
            Recipe recipeSave=recipeOptional.get();
            Optional<Ingerediant> ingeredianttoDelet=recipeSave.getIngerediants().stream()
                    .filter(ingerediant -> ingerediant.getId().equals(ingredientId))
                    .findFirst();
            if(ingeredianttoDelet.isPresent()){
                Ingerediant ingerediant=ingeredianttoDelet.get();
                ingerediant.setRecipe(null);
                recipeSave.getIngerediants().remove(ingerediant);
                recipeRepository.save(recipeSave);

            }
            log.error("this ingredient does not exist with this id : "+ingredientId);
        }
        else {
            log.error("this recipe does not exist with this id: " +recipeId);
        }


    }
}
