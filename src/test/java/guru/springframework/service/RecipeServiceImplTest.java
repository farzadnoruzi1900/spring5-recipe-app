package guru.springframework.service;

import guru.springframework.module.Recipe;
import guru.springframework.repository.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {
    /*imagine we want to test this service , so this is mock test becasuse this method use repository
        and we need to use mock annotation here with alt+INs you can go and use setUp method
        the way you declare constructore or getter and setter .  */
    RecipeServiceImpl recipeService;
    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        // this is how we inject the repository object to this class
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
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
        verify(recipeRepository,times(1)).findAll();

    }
}