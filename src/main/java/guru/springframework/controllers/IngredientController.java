package guru.springframework.controllers;

import guru.springframework.services.IngredeiantService;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IngredientController {

   private final RecipeService recipeService;
   private final IngredeiantService ingredeiantService;


    public IngredientController(RecipeService recipeService, IngredeiantService ingredeiantService) {
        this.recipeService = recipeService;
        this.ingredeiantService = ingredeiantService;
    }

    @RequestMapping("recipe/{id}/ingerediants")
    public String showIngerediantList(@PathVariable String id, Model model){
        log.debug("Getting ingredient list for recipe id: " + id);
        // use command object to avoid lazy load errors in Thymeleaf.
        // but the problem which I have here is that in thymleaf I encountered parsing thymleaf
        // error while I am passing a command recipe object to it because it is working with module objects
        // just remmember it is a very good practice to use command but in case the view part of the code
        // is compatible with this change .
        //model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));
        model.addAttribute("recipe",recipeService.findById(Long.valueOf(id)));
                return "recipe/ingerediants/index";
    }

    @RequestMapping("recipe/{recipeId}/ingerediant/{ingerediantId}/show")
    public String showIngerediantById(@PathVariable String recipeId,
                                      @PathVariable String ingerediantId,Model model){
        model.addAttribute("ingredient",
                ingredeiantService.findIngerediantbyIdWithRecipeId(
                        Long.valueOf(recipeId),Long.valueOf(ingerediantId)));
return "recipe/ingerediants/show";
    }

}
