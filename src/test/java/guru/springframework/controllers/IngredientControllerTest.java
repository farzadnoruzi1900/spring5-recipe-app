package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.module.Recipe;
import guru.springframework.services.IngredeiantService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class IngredientControllerTest {
    @Mock
    RecipeService recipeService;
    @Mock
    IngredeiantService ingredeiantService;
    @Mock
    UnitOfMeasureService unitOfMeasureService;
    IngredientController ingredientController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.ingredientController = new IngredientController(
                recipeService, ingredeiantService, unitOfMeasureService);
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    public void showIngerediantList() throws Exception {
        //Given
        Recipe recipe = new Recipe();
        when(recipeService.findById(anyLong())).thenReturn(recipe);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("recipe/1/ingerediants"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingerediants/index"))
                .andExpect(model().attributeExists("recipe"));

        //then
        verify(recipeService, times(1)).findCommandById(anyLong());
    }


    @Test
    public void showIngerediantById() {
    }

    @Test
    public void updateRecipeIngredient() {
    }
}