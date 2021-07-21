package guru.springframework.services;

import guru.springframework.Exceptions.NotFoundException;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.module.Recipe;
import guru.springframework.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             RecipeCommandToRecipe recipeCommandToRecipe,
                             RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipe() {
        log.debug("we are in serviec");
        // how we use the log object without creating the property is because of the sfl4j lombok
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().forEach(recipeSet::add);
        //recipeRepository.findAll return Itarable<> which can not be passed to List so thats
        //why we declare a set to add all the members of iterable class to that list and return it as result of this
        //operation.
        return recipeSet;
        //iterator().forEachRemaining(recipeSet::add);

    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> optionalRecipe=recipeRepository.findById(id);
        if(!optionalRecipe.isPresent()) {
            throw new NotFoundException("there is not any recipe for this id value :" + id.toString());
        }
        else {
            return optionalRecipe.get();
        }
    }

    @Override
    @Transactional
    // notice in here recipe and other dependent classes inside recipe
    /*such as Ingerediant or Notes or Catagory all are cascaded by the recipe
    so when we save recipe all of them will save automatically . */
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
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachRecipe = recipeCommandToRecipe.convert(command);
        Recipe saveRecipe = recipeRepository.save(detachRecipe);
        return recipeToRecipeCommand.convert(saveRecipe);
    }

    // when we are doing thngs with web-tier as we said the command objects go throw the view template
//    thats why these methods return command object.
    @Override
    @Transactional
    public RecipeCommand updateRecipeById(Long id) {

        return recipeToRecipeCommand.convert(findById(id));
    }

    @Override
    public void deletById(Long id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public RecipeCommand findCommandById(Long id) {
        RecipeCommand recipeCommand=recipeToRecipeCommand.convert(findById(id));
        return recipeCommand;
    }

}
