package guru.springframework.converters;

import guru.springframework.commands.IngrediantCommand;
import guru.springframework.module.Ingerediant;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngradiantToIngradiantCommand implements Converter<Ingerediant, IngrediantCommand> {


    @Synchronized
    @Nullable
    @Override
    public IngrediantCommand convert(Ingerediant source) {
        if(source==null){
            return null ;
        }
        IngrediantCommand ingrediantCommand=new IngrediantCommand();
        ingrediantCommand.setAmount(source.getAmount());
        ingrediantCommand.setDescription(source.getDescription());
        ingrediantCommand.setId(source.getId());
        return ingrediantCommand;
    }
}
