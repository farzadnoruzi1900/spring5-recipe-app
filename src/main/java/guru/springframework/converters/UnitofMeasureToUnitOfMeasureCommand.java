package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.module.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


@Component
public class UnitofMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {


    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {
        if(source==null){
            return null;
        }
        UnitOfMeasureCommand unitOfMeasureCommand =new UnitOfMeasureCommand();
        unitOfMeasureCommand.setDescription(source.getDescription());
        unitOfMeasureCommand.setId(source.getId());

        return unitOfMeasureCommand;
    }
}
