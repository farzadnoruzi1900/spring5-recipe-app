package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.module.Recipe;
import guru.springframework.repository.RecipeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceImplIT {
    // there is something so interesting about writing this class test separated from
    //RecipeServiceImpTest and that is because we are gonna test the method which is use
    //transactional annotation so it is supposed to be a integration test
//    Integration testing plays an important role in the application development cycle by
//    verifying the end-to-end behavior of a system.

/*

    Spring’s integration testing support has the following primary goals:

    To manage Spring IoC container caching between tests.

    To provide Dependency Injection of test fixture instances.

  ++  To provide transaction management appropriate to integration testing.

    To supply Spring-specific base classes that assist developers in writing integration tests.

    The next few sections describe each goal and provide links to implementation and configuration details.
*/

    private final String Description="description";
    @Autowired
   RecipeService recipeService;
    @Autowired
   RecipeRepository recipeRepository;
    @Autowired
   RecipeCommandToRecipe recipeCommandToRecipe;
    @Autowired
   RecipeToRecipeCommand recipeToRecipeCommand;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    @Transactional
    public void saveRecipeCommand() {
        Optional<Recipe> first = StreamSupport.stream(recipeRepository.findAll().spliterator(), false)
                .findFirst();
        Recipe recipe=first.get();
        RecipeCommand testCommand=recipeToRecipeCommand.convert(recipe);
        testCommand.setDescription(Description);
//        when(recipeToRecipeCommand.convert(any())).thenReturn(testCommand);
        // verify(recipeRepository,times(1)).save(any());
/*something so important to add so we learned how to conduct this type of test for our services
                because as you know the integration test we have it for our controller which
                has the comminication with different module and web-tier but in here we used it because
                this method is transactional . we can also use mock to do the test but in this
                specific example he is used @RunWith(SpringRunner.class)
                @SpringBootTest this type of integration testing and thats why we are not allowed to use
                when or verify which are mock tools here in this test method .*/
        // I wrote the mock test for transactional method in ingrediant service .
        Assert.assertEquals(recipe.getId(),testCommand.getId());

        RecipeCommand saveCommand=recipeService.saveRecipeCommand(testCommand);

        Assert.assertEquals(Description,saveCommand.getDescription());
        assertEquals(testCommand.getCatagories().size(),saveCommand.getCatagories().size());
        assertEquals(testCommand.getIngredients().size(),saveCommand.getIngredients().size());


    }

}