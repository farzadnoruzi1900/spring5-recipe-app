package guru.springframework.controllers;

import guru.springframework.repository.CatagoryRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexCoroller {
    private final CatagoryRepository catagoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeService recipeService;

    public IndexCoroller(CatagoryRepository catagoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeService recipeService) {
        this.catagoryRepository = catagoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
    }


    @GetMapping({"/index", ""})
    public String getIndex(Model model) {
        // in here we get access to those query method and we store them in Optional class again for purpose
        //of not having point to null object and then we easily fetch data from database .
       /*Optional<Catagory> catagory=catagoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasure=unitOfMeasureRepository.findByDescription("Cup");
        System.out.println("cat Id is : "+catagory.get().getId());
        System.out.println("unit id is : "+unitOfMeasure.get().getId());*/
        model.addAttribute("recipes", recipeService.getRecipe());

        return "index";
    }
    @RequestMapping("/recipe/indexTest")
    public String getRecipe(Model model){
        log.debug("we are in getRecipe method");
        model.addAttribute("recipes",recipeService.getRecipe());
        return "recipe/recipeIndex";
    }



}
