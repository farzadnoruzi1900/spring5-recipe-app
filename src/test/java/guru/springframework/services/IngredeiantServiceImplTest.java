package guru.springframework.services;

import guru.springframework.commands.IngrediantCommand;
import guru.springframework.converters.IngradiantToIngradiantCommand;
import guru.springframework.converters.UnitofMeasureToUnitOfMeasureCommand;
import guru.springframework.module.Ingerediant;
import guru.springframework.module.Recipe;
import guru.springframework.repository.RecipeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredeiantServiceImplTest {
    IngradiantToIngradiantCommand ingradiantToIngradiantCommand;
    @Mock
    RecipeRepository recipeRepository;

    IngredeiantService ingredeiantService;
    @Before
    public void setUp() throws Exception {
        ingradiantToIngradiantCommand=new IngradiantToIngradiantCommand
                (new UnitofMeasureToUnitOfMeasureCommand());
        MockitoAnnotations.initMocks(this);
        this.ingredeiantService=new IngredeiantServiceImpl(recipeRepository,ingradiantToIngradiantCommand);
    }

    @Test
    public void findIngerediantbyIdWithRecipeId() {
        //to do the test we have a given part which imitate the spring functionality
        //we need some objects to work with.
        //then we have to test the services or repositories that we use in our mehtod
//        that we are writing test for

        //given
        Recipe recipe=new Recipe();
        recipe.setId(1l);
        Ingerediant ingerediant1=new Ingerediant();
        ingerediant1.setId(1l);
        Ingerediant ingerediant2=new Ingerediant();
        ingerediant2.setId(2l);
        Ingerediant ingerediant3=new Ingerediant();
        ingerediant3.setId(3l);
        recipe.addIngrediant(ingerediant1);
        recipe.addIngrediant(ingerediant2);
        recipe.addIngrediant(ingerediant3);
        Optional<Recipe> optionalRecipe=Optional.of(recipe);
        //to be sure the reiperepo return an optional
        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);
//then
        //we have to be sure that our method work properly .
        IngrediantCommand ingrediantCommand=ingredeiantService.findIngerediantbyIdWithRecipeId(1l,3l);

        //when
        Assert.assertEquals(Long.valueOf(3l),ingrediantCommand.getId());
        Assert.assertEquals(Long.valueOf(1l),ingrediantCommand.getRecipeId());
        //to be sure that it runs at least once
        verify(recipeRepository,times(1)).findById(anyLong());

    }
}