package guru.springframework.services;

import guru.springframework.module.Recipe;
import guru.springframework.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {
    private final RecipeRepository recipeRepository;


    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {
        try {
            Recipe recipe = recipeRepository.findById(recipeId).get();
            Byte[] objectByte = new Byte[file.getBytes().length];
            int i = 0;
            for (Byte b : file.getBytes()) {
                objectByte[i++] = b;
            }
            recipe.setImage(objectByte);
            recipeRepository.save(recipe);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
