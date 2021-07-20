package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.ImageService;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@Slf4j
public class ImageController {
    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }
    @GetMapping("recipe/{recipeId}/image")
    public String showUploadImageForm(@PathVariable String recipeId, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));
        return "recipe/imageuploadform";
    }

    @PostMapping("recipe/{recipeId}/image")
    public String uploadImage(@PathVariable String recipeId, @RequestParam("imagefile") MultipartFile multipartFile) {
        imageService.saveImageFile(Long.valueOf(recipeId), multipartFile);
        return "redirect:/recipe/" + recipeId + "/show";
    }

    @GetMapping("recipe/{recipeId}/recipeimage")
    public void readImageFromDB(@PathVariable String recipeId, HttpServletResponse response) {
//        this method shows the recipe image on the browser
  /*      in the post method we recieved the image from outside source and persist it to database
                and then by redirecting to that url show that picture as the image of the recipe .
        in here we want to assiigne one of the picture in the database as response type of this url
            that we have here .*/

        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
        if (recipeCommand.getImage() != null) {
            byte[] bytes = new byte[recipeCommand.getImage().length];
            int i = 0;
            for (Byte b : recipeCommand.getImage()) {
                bytes[i++] = b;
            }
            response.setContentType("image/jpeg");
            try (InputStream is = new ByteArrayInputStream(bytes)) {
             /*   in here we put the image byte array in the inputstream and then we copy that
                        input in to respons output mehtod .*/
                IOUtils.copy(is, response.getOutputStream());
            } catch (IOException ex) {
                ex.getMessage();
            }
        } else {
            log.error("there is not any related image to this recipe by this id : " + recipeId);
        }


    }
}
