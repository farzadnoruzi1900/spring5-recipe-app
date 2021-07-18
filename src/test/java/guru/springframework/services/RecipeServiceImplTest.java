package guru.springframework.services;

import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.module.Recipe;
import guru.springframework.repository.RecipeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {
    /*imagine we want to test this service , so this is mock test becasuse this method use repository
        and we need to use mock annotation here with alt+INs you can go and use setUp method
        the way you declare constructore or getter and setter .  */
    RecipeServiceImpl recipeService;
    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    private final Long id=1l;
    @Before
    public void setUp() throws Exception {
        // this is how we inject the repository object to this class
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository,
                recipeCommandToRecipe,
                recipeToRecipeCommand);
    }

    @Test
    public void getRecipe() {
        Recipe recipe = new Recipe();
        Set<Recipe> recipeData = new HashSet<>();
        recipeData.add(recipe);
        // this is how we are using mockito test with help of when
        //  we are saying get all the recipes from repository and then we want to check when we are using
//        recipeRepository we receive the same file that we created
        when(recipeRepository.findAll()).thenReturn(recipeData);
        Set<Recipe> recipeSet = recipeService.getRecipe();
        // another method in mockito and integration test is to use verify and
    /*    by that we want to assure that that specific method runs only and only one time
                in here .*/
        Assert.assertEquals(recipeSet.size(),recipeData.size());
        verify(recipeRepository,times(1)).findAll();

    }
    @Test
    public void getRecipeTestById(){
      /*  just some more explanation again in writing test you have a method for instace
                in your seice that you want to test it in budy of your test more than the classes and
                methods which are in your under test method you have to examin the method itself
                in here we haveto test recipeService.findById too rather than recipeRepository*/

         //given
        Recipe recipe=new Recipe();
        recipe.setId(id);
        Optional<Recipe> optionalRecipe= Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        //when
        Recipe recipe1=recipeService.findById(id);

        //then
        Assert.assertEquals(recipe.getId(),recipe1.getId());
        Assert.assertNotNull(recipe1);
        verify(recipeRepository).findById(anyLong());
        verify(recipeRepository,never()).findAll();

    }
    @Test
    public void deletByIdTest(){
        Long idDelete=Long.valueOf(1l);
        recipeService.deletById(idDelete);
/*
it does not have any return type so we do not have any when
*/
        verify(recipeRepository,times(1)).deleteById(anyLong());
    }
}