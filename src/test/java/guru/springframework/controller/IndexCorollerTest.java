package guru.springframework.controller;

import guru.springframework.module.Recipe;
import guru.springframework.repository.CatagoryRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import guru.springframework.service.RecipeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexCorollerTest {
    @Mock
    RecipeService recipeService;
    @Mock
    Model model;
    @Mock
    CatagoryRepository catagoryRepository;
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;
    IndexCoroller indexCoroller;

    @Before
    public void setUp() throws Exception {
       /* MockitoAnnotations.initMocks(this.model);
        MockitoAnnotations.initMocks(this.recipeService);*/
        //by using only this you can inject both of those interfaces which has mock annotation
        MockitoAnnotations.initMocks(this);

        indexCoroller = new IndexCoroller(catagoryRepository, unitOfMeasureRepository, recipeService);


    }

    // this is how we test spring mvc controllers .
    @Test
    public void mockMvc() throws Exception {
/*
        totally we use MockMvc to test the controllers in our application .
*/
        /*
        there are two options for mockmvcbuilders one is standalone and one is webappcontext
                the first one is going for having a unit test which is faster and the second one
                grab all the objects in spring context with which might be a little bit slower*/
//        the get is from MockMvcRequestBuilders when you want to import static method.
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(indexCoroller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("recipe/recipeIndex"));
    }

    @Test
    public void getRecipe() {
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());
        Recipe recipe = new Recipe();
        recipe.setId(4l);
        recipes.add(recipe);
        Mockito.when(recipeService.getRecipe()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor=ArgumentCaptor.forClass(Set.class);

        String value = indexCoroller.getRecipe(model);
        Assert.assertEquals("recipe/recipeIndex", value);

        Mockito.verify(recipeService, Mockito.times(1)).getRecipe();
        /*the seconde attribute of the addAttribute is set thats why we use anySet and then we use eq to say
                the value it returns must be equal to recipe.*/
        //instead of setAny you can use argumentCaptor.capture()
       // Mockito.verify(model, Mockito.times(1)).addAttribute(eq("recipe"), anySet());
        Mockito.verify(model, Mockito.times(1)).addAttribute(eq("recipe"), argumentCaptor.capture());

        Set<Recipe> recipes1=argumentCaptor.getValue();
        Assert.assertEquals(2,recipes1.size());
    }
}