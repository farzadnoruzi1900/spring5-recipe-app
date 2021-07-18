package guru.springframework.controllers;

import guru.springframework.commands.IngrediantCommand;
import guru.springframework.services.IngredeiantService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class IngredientController {

   private final RecipeService recipeService;
   private final IngredeiantService ingredeiantService;
   private final UnitOfMeasureService unitOfMeasureService;


    public IngredientController(RecipeService recipeService, IngredeiantService ingredeiantService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredeiantService = ingredeiantService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping
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

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingerediant/{ingerediantId}/show")
    public String showIngerediantById(@PathVariable String recipeId,
                                      @PathVariable String ingerediantId,Model model){
        model.addAttribute("ingredient",
                ingredeiantService.findIngerediantbyIdWithRecipeId(
                        Long.valueOf(recipeId),Long.valueOf(ingerediantId)));
return "recipe/ingerediants/show";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                            @PathVariable String id,Model model){
        model.addAttribute("ingredient",ingredeiantService.findIngerediantbyIdWithRecipeId
                (Long.valueOf(recipeId),Long.valueOf(id)));
        model.addAttribute("uomList",unitOfMeasureService.getAllUom());
        return "recipe/ingerediants/ingerediantform";
    }

    @PostMapping
    @RequestMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdateIngredient(@ModelAttribute IngrediantCommand command){
        IngrediantCommand ingrediantCommand=ingredeiantService.saveIngrediant(command);

        log.debug("saved receipe id:" + ingrediantCommand.getRecipeId());
        log.debug("saved ingredient id:" + ingrediantCommand.getId());
        return "redirect:/recipe/"+command.getRecipeId()+"/ingerediant/"+ command.getId()+"/show";


    }
    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingrediant/{ingredientId}/delete")
public String deleteIngredientById(@PathVariable String recipeId,@PathVariable String ingredientId){
        ingredeiantService.deleteIngredientById(Long.valueOf(recipeId),Long.valueOf(ingredientId));
        return "redirect:/recipe/"+recipeId+"/ingerediants";
}

}
