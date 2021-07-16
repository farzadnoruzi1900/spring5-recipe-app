package guru.springframework.converters;

import guru.springframework.commands.CatagoryCommand;
import guru.springframework.module.Catagory;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CatagoryToCatagoryCommand implements Converter<Catagory, CatagoryCommand> {


    @Synchronized
    @Nullable
    @Override
    public CatagoryCommand convert(Catagory source) {
        if(source==null){
            return null ;
        }
        CatagoryCommand catagoryCommand=new CatagoryCommand();
        catagoryCommand.setDescription(source.getDescription());
        catagoryCommand.setId(source.getId());
        return catagoryCommand;
    }
}
