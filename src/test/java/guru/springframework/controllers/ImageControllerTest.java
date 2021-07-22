package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.ImageService;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ImageControllerTest {
    @Mock
    ImageService imageService;
    @Mock
    RecipeService recipeService;
    ImageController imageController;
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.imageController = new ImageController(imageService, recipeService);
       /* when we are using @ControllerAdvice to have globalized exceptionHandler we have to instanciate
                mockMvc in this way.*/
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).
                setControllerAdvice(new ExceptionHandlerController()).build();
        /*by doing this we are wiring in the controller advise so that is happening inside of our setUp
                for mockMvc a we do not rely the pull spring context in this way it is faster .*/

    }

    @Test
    public void showUploadImageForm() throws Exception {
        RecipeCommand recipeCommand=new RecipeCommand();
        recipeCommand.setId(1L);
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/imageuploadform"))
                .andExpect(model().attributeExists("recipe"));

        verify(recipeService,times(1)).findCommandById(anyLong());

    }

    @Test
    public void uploadImage() throws Exception {
        MockMultipartFile multipartFile=new MockMultipartFile("imagefile",
                "testinkkg.txt", MediaType.TEXT_PLAIN_VALUE,"spring Framework Farzad".getBytes());
// the media type and the first attribute which is name is important the rest is mimicking

        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/recipe/1/show"));

        verify(imageService, times(1)).saveImageFile(anyLong(), any());
    }
    @Test
    public void handleNumberFormatExceptionTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/dfdaf/image"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("exceptions/400view"));
    }



}