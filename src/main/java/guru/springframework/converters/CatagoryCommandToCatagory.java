package guru.springframework.converters;

import guru.springframework.commands.CatagoryCommand;
import guru.springframework.module.Catagory;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CatagoryCommandToCatagory implements Converter<CatagoryCommand, Catagory> {


    @Synchronized
    @Nullable
    @Override
    public Catagory convert(CatagoryCommand source) {
        if(source==null){
            return null;
        }
       final Catagory catagory=new Catagory();
        catagory.setId(source.getId());
        catagory.setDescription(source.getDescription());

        return catagory;
    }
}
