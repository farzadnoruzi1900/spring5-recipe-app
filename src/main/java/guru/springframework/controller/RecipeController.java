package guru.springframework.controller;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.text.normalizer.NormalizerBase;

@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    //    this is how you declare variable in the url
    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("recipe",recipeService.findById(Long.valueOf(id)));
        return "recipe/show";
    }
    @RequestMapping("recipe/new")
    public String getNewRecipe(Model model){
        model.addAttribute("recipe",new RecipeCommand());
        return "recipe/recipeform";
    }

    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.findById(Long.valueOf(id)));
        return "recipe/recipeform";
    }
    //the model attribute is for the time that we have post or updata and
   /* we expect to recive an object from web-tier by modulattribute we are saying bind that attribute
    to RecipeCommand .*/
    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdateNewRecipe(@ModelAttribute RecipeCommand command){
        RecipeCommand recipeCommand=recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/"+recipeCommand.getId()+"/show";
// in here we say apply the saveRecipeCommand method on the new Recipe which you are
        /*receiving from web-tier and after that redirect the page to show.html and return
                the new object that you saved .*/
        // in redirect it does not return any form from template it goes to that url address
      /*  that you want to redirect it . so in here go to show/id and by id as you can see in showById
                mehtod it will show the recipe specific to that id */
    }

}
