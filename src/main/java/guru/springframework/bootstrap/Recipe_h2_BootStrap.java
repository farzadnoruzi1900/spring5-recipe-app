package guru.springframework.bootstrap;

import guru.springframework.module.*;
import guru.springframework.repository.CatagoryRepository;
import guru.springframework.repository.RecipeRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@Profile("default")
public class Recipe_h2_BootStrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CatagoryRepository catagoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public Recipe_h2_BootStrap(CatagoryRepository catagoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.catagoryRepository = catagoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(recipeSetReturn());
    }


    private Set<Recipe> recipeSetReturn() {
        Set<Recipe> recipeSet = new HashSet<>(2);
        //fetch the UnitOfMeasure form DataBase
        Optional<UnitOfMeasure> teaSpoonOP = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!teaSpoonOP.isPresent()) {
            throw new RuntimeException("the teaSpoon unitOfMeasure is not exist");
        }
        Optional<UnitOfMeasure> tableSpoonOP = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (!tableSpoonOP.isPresent()) {
            throw new RuntimeException("the Tablespoon unitOfMeasure is not exist");
        }
        Optional<UnitOfMeasure> cupOP = unitOfMeasureRepository.findByDescription("Cup");
        if (!cupOP.isPresent()) {
            throw new RuntimeException("the Cup unitOfMeasure is not exist");
        }
        Optional<UnitOfMeasure> pinchOP = unitOfMeasureRepository.findByDescription("Pinch");
        if (!pinchOP.isPresent()) {
            throw new RuntimeException("the Pinch unitOfMeasure is not exist");
        }
        Optional<UnitOfMeasure> ounceOP = unitOfMeasureRepository.findByDescription("Ounce");
        if (!ounceOP.isPresent()) {
            throw new RuntimeException("the Ounce unitOfMeasure is not exist");
        }
        Optional<UnitOfMeasure> eachOP = unitOfMeasureRepository.findByDescription("Each");
        if (!eachOP.isPresent()) {
            throw new RuntimeException("the Each unitOfMeasure is not exist");
        }
        Optional<UnitOfMeasure> dashOP = unitOfMeasureRepository.findByDescription("Dash");
        if (!dashOP.isPresent()) {
            throw new RuntimeException("the Dash unitOfMeasure is not exist");
        }
        Optional<UnitOfMeasure> pintOP = unitOfMeasureRepository.findByDescription("Pint");
        if (!pintOP.isPresent()) {
            throw new RuntimeException("the Pint unitOfMeasure is not exist");
        }
        //fetch the data for the catagory from dataBase
        Optional<Catagory> americaOP = catagoryRepository.findByDescription("American");
        if (!americaOP.isPresent()) {
            throw new RuntimeException("the American catagory does not exist in dataBase");
        }
        Optional<Catagory> italianOP = catagoryRepository.findByDescription("Italian");
        if (!italianOP.isPresent()) {
            throw new RuntimeException("the Italian catagory does not exist in dataBase");
        }
        //get the UnitOfMeasure Optionals
        UnitOfMeasure pint = pinchOP.get();
        UnitOfMeasure dash = dashOP.get();
        UnitOfMeasure each = eachOP.get();
        UnitOfMeasure ounce = ounceOP.get();
        UnitOfMeasure pinch = pinchOP.get();
        UnitOfMeasure cup = cupOP.get();
        UnitOfMeasure teaSpoon = teaSpoonOP.get();
        UnitOfMeasure tableSpoon = tableSpoonOP.get();


        //get the Catagory Optionals
        Catagory america = americaOP.get();
        Catagory italy = italianOP.get();

        Recipe guacRecipe = new Recipe();
        //declaring Note
        Notes guaNotes = new Notes();
       // guaNotes.setRecipe(guacRecipe);
        guaNotes.setDirection("\"For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\\n\" +\n" +
                "                \"Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\\n\" +\n" +
                "                \"The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\\n\" +\n" +
                "                \"To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws\"");


        guacRecipe.getCatagories().add(america);
        guacRecipe.setCookTime(10);
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.addIngrediant(new Ingerediant("ripe avocados", new BigDecimal(2), each));
        guacRecipe.addIngrediant(new Ingerediant("Kosher salt", new BigDecimal(".5"),  teaSpoon));
        guacRecipe.addIngrediant(new Ingerediant("fresh lime juice or lemon juice", new BigDecimal(2),  tableSpoon));
        guacRecipe.addIngrediant(new Ingerediant("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoon));
        guacRecipe.addIngrediant(new Ingerediant("serrano chiles, stems and seeds removed, minced", new BigDecimal(2),  each));
        guacRecipe.addIngrediant(new Ingerediant("Cilantro", new BigDecimal(2), tableSpoon));
        guacRecipe.addIngrediant(new Ingerediant("freshly grated black pepper", new BigDecimal(2),  dash));
        //change this old way of adding ingrediant with a new mehtod addIngerediant in recipe.
        //guacRecipe.getIngerediantSet().add(new Ingerediant("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), guacRecipe, each));
        guacRecipe.addIngrediant(new Ingerediant("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), each));

        guacRecipe.setDifficulity(Difficulity.EASY);
        guacRecipe.setDirections("\"1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon\" +\n" +
                "                \"\\n\" +\n" +
                "                \"2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\" +\n" +
                "                \"\\n\" +\n" +
                "                \"3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\\n\" +\n" +
                "                \"Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\\n\" +\n" +
                "                \"Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\\n\" +\n" +
                "                \"4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\\n\" +\n" +
                "                \"Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd\"");

        guacRecipe.setNotes(guaNotes);
        recipeSet.add(guacRecipe);
        return recipeSet;
    }
}


