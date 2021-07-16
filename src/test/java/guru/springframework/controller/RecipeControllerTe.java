package guru.springframework.controller;

import guru.springframework.module.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class RecipeControllerTe {
    @Mock
    RecipeService recipeService;
    RecipeController recipeController;
    MockMvc mockMvc;
    Recipe recipe;

    @Before
    public void setUp() throws Exception {
        recipeController=new RecipeController(recipeService);

        recipe=new Recipe();
    }

    @Test
    public void showById() throws Exception {
        recipe.setId(1L);
        mockMvc= MockMvcBuilders.standaloneSetup(recipeController).build();

       when(recipeService.findById(anyLong())).thenReturn(recipe);
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/show/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/show"));
    }
}