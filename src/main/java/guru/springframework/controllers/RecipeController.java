package guru.springframework.controllers;

import guru.springframework.Exceptions.NotFoundException;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    //  this is how you declare variable in the url

    @GetMapping("/recipe/{id}/show")
    //@RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("recipe",recipeService.findById(Long.valueOf(id)));
        return "recipe/show";
    }

    @GetMapping("recipe/new")
    //@RequestMapping("recipe/new")
    public String getNewRecipe(Model model){
        model.addAttribute("recipe",new RecipeCommand());
        return "recipe/recipeform";
        // we are getting an empty object to recipeform and then asign the value to it
        /*then by clicking to submit it will trigger a post method on this url
            and persist the object to database with help of method we wrote on seervice*/
    }

    @GetMapping("recipe/{id}/update")
    //@RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.updateRecipeById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    //the model attribute is for the time that we have post or updata and
   /* we expect to recive an object from web-tier by modulattribute we are saying bind that attribute
    to RecipeCommand .*/

    @PostMapping("recipe")
    //@RequestMapping("recipe")
    public String saveOrUpdateNewRecipe(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult) {
        /*the reason why we are using bindingResult is that now on our recipeCommand we have
                built in validator annotation and we need to show those errors in case of happening
                in our trace.it is good to check the exact reason of errors .there are some new
                methods in writing the tests for this method too you can find it by this name
                postNewRecipeForm*/
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError ->
                    log.error(objectError.toString()));
         /*   I have learnt something sweet from attribute in  @ModelAttribute(attribute), this is the
                    object that you are passing it to the thymleaf it means we put the command as
                    object in to the recipe and then as you see we use this object "recipe " inside the
                    thymleaf so it is important to use it when we want to reject to our template if we do not want to
                    return to thymleaf and put the command object to recipe there is no need to use ModelAttribute
                    it is used to catch an object in module then use it in template or code */

            return "recipe/recipeform";
        }
        RecipeCommand recipeCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/" + recipeCommand.getId() + "/show";
// in here we say apply the saveRecipeCommand method on the new Recipe which you are
        /*receiving from web-tier and after that redirect the page to show.html and return
                the new object that you saved .*/
        // in redirect it does not return any form from template it goes to that url address
      /*  that you want to redirect it . so in here go to show/id and by id as you can see in showById
                mehtod it will show the recipe specific to that id */

//        the object in post method must be only and only command
//        do not forget that the object that we receive is command object
    }

    @GetMapping("recipe/{id}/delete")
    //@RequestMapping("recipe/{id}/delete")
    public String deletById(@PathVariable String id) {
        recipeService.deletById(Long.valueOf(id));
        return "redirect:/";
    }

    //@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {
        log.error(exception.getMessage());
      /*  by adding exceptionHandler we want to say that when ever we catch the error of the
                class NotFoundException brows this view and of course we have to put the status code here
                too as we put it on notFoundException Class. whether decklare it with annotation
                or with modelAndView object .*/
        log.error("handling Not found Exception");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
     /*   as you can see by using addObject you can pass object to your model
                and then show its content as here we have exception which we want to show its message.*/
        modelAndView.addObject("exception",exception);
        modelAndView.setViewName("exceptions/404view");
        return modelAndView;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
   /* the idea here as you know is to catch the NumberFormatException
            where we need our client to put number but what we receive is string
            as you know this exception might not only happen for recipe
    controller and might happen for imagecontroller or ingredient controller also so we
    need this error handling be global for all the places that such exception
            and we want to pass it to the view that we design for this .so
    what we have to do is to move this handler to a separate controller class called @ControllerAdvice*/
    public ModelAndView handleFileFormatException(Exception exception){
        log.error(exception.getMessage());
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("exception",exception);
        modelAndView.setViewName("exceptions/400view");
        return modelAndView;
    }

}
